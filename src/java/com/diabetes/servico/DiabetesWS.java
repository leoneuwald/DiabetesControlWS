/*s
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diabetes.servico;

import com.diabetes.model.Medico;
import com.diabetes.model.NotaRegistroMedico;
import com.diabetes.model.Paciente;
import com.diabetes.model.Registro;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author Leonardo
 */
@WebService(serviceName = "DiabetesWS")
@Stateless()
public class DiabetesWS {

    /**
     * Operação de serviço web
     */
    @WebMethod(operationName = "addRegistro")
    public String addRegistro(@WebParam(name = "tipo") String tipo, @WebParam(name = "categoria") String categoria, @WebParam(name = "valor") Float valor, @WebParam(name = "datahora") Date datahora, @WebParam(name = "codpaciente") String codpaciente, @WebParam(name = "unidade") String unidade, @WebParam(name = "idcelular") Integer idcelular, @WebParam(name = "idmedicamento") Integer idmedicamento, @WebParam(name = "valorpressao") String valorpressao) {
        try {
            new Registro(null, tipo, categoria, valor, datahora, null, codpaciente, unidade, idcelular, "N", valorpressao, idmedicamento).salvar();
        } catch (Exception e) {
            e.getStackTrace();
            return "error";
        }
        return "sucess";
    }
    
    /**
     * Operação de serviço web
     */
    @WebMethod(operationName = "cadNotaMedico")
    public String cadNotaMedico(@WebParam(name = "descricao") String descricao, @WebParam(name = "infoRegistro") String infoRegistro, @WebParam(name = "codPaciente") String codPaciente, @WebParam(name = "idCelular") Integer idCelular) {
        try {
            new NotaRegistroMedico(null, descricao, "N", infoRegistro, codPaciente, idCelular).salvar();
        } catch (Exception e) {
            e.getStackTrace();
            return "error";
        }
        return "sucess";
    }

    /**
     * Operação de serviço web
     */
    @WebMethod(operationName = "addPacienteMedico")
    public String[] addPacienteMedico(@WebParam(name = "codPaciente") String codPaciente, @WebParam(name = "senhaPaciente") String senhaPaciente) {
        String[] mensagens = new String[10];
        try {
            Paciente pac = new Paciente().getPacienteCodSenha(codPaciente, senhaPaciente);
            if (pac == null) {
                mensagens[0] = "Verifique senha e usuário do Paciente!";
            } else {
                //Parâmetros passados para construção do Paciente no Android!
                mensagens[0] = "sucess";
                mensagens[1] = pac.getNomePac();
                mensagens[2] = pac.getEmailPac();
                mensagens[3] = pac.getCodPac();
            }
            return mensagens;
        } catch (Exception e) {
            e.printStackTrace();
            mensagens[0] = "Erro ao buscar senha e usuário do Paciente!";
            return mensagens;
        }
    }

    /**
     * Operação de serviço web
     */
    @WebMethod(operationName = "cadPaciente")
    public String cadPaciente(@WebParam(name = "nomePac") String nomePac, @WebParam(name = "emailPac") String emailPac, @WebParam(name = "codPaciente") String codPaciente, @WebParam(name = "sexoPac") String sexoPac, @WebParam(name = "nascimentoPac") Date nascimentoPac, @WebParam(name = "senhaPac") String senhaPac, @WebParam(name = "update") String update) {
        try {
            if ("S".equals(update)){
                new Paciente(codPaciente, nomePac, sexoPac, senhaPac, emailPac, nascimentoPac).salvar();
            } else {
                Paciente pac = new Paciente();
                if(pac.getPaciente(codPaciente) != null){
                    return "duplicado";
                } else {
                    new Paciente(codPaciente, nomePac, sexoPac, senhaPac, emailPac, nascimentoPac).salvar();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "error";
        }
        return "sucess";
    }

    /**
     * Operação de serviço web
     */
    @WebMethod(operationName = "addNotaRegistro")
    public String addNotaRegistro() {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Operação de serviço web
     */
    @WebMethod(operationName = "addMedico")
    public String addMedico(@WebParam(name = "nomeMed") String nomeMed, @WebParam(name = "emailMed") String emailMed, @WebParam(name = "codMedico") String codMedico, @WebParam(name = "registroMed") String registroMed) {
        try {
            new Medico(nomeMed, emailMed, nomeMed, codMedico).salvar();
        } catch (Exception e) {
            e.getStackTrace();
            return e.getMessage().toString();
        }
        return "Sucesso";
    }

    /**
     * Operação de serviço web
     */
    @WebMethod(operationName = "getRegistrosPaciente")
    public List<String[]> getRegistrosPaciente(@WebParam(name = "codPaciente") String codPaciente) {
        List<String[]> listString = new ArrayList<String[]>();
        String[] mensagens = null;
        try {
            List<Registro> list = new Registro().getRegistros(codPaciente);
            if (list == null) {
                mensagens = new String[1];
                mensagens[0] = "Sem Registros!";
                listString.add(mensagens);
            } else {
                for (Registro item : list) {
                    mensagens = new String[9];
                    mensagens[0] = "sucess";
                    if (item.getValor() != null) {
                        mensagens[1] = item.getValor().toString();
                    } else {
                        mensagens[1] = item.getValorpressao();
                    }
                    mensagens[2] = item.getCategoria();
                    mensagens[3] = item.getTipo();
                    mensagens[4] = item.getCodPaciente();
                    mensagens[5] = item.getUnidade();
                    mensagens[6] = item.getIdCelular().toString();
                    mensagens[7] = String.valueOf(item.getDatahora().getTime());
                    if (item.getIdMedicamento() != null) {
                        mensagens[8] = item.getIdMedicamento().toString();
                    } else {
                        mensagens[8] = null;
                    }
                    listString.add(mensagens);
                    item.setSincronizado("S");
                    new Registro().update(item);
                }
            }
            return listString;
        } catch (Exception e) {
            e.getStackTrace();
            e.printStackTrace();
            mensagens[0] = "Erro ao buscar Registros!";
            listString.add(mensagens);
            return listString;
        }
    }
    
     @WebMethod(operationName = "getNotasMedicas")
    public List<String[]> getNotasMedicas(@WebParam(name = "codPaciente") String codPaciente) {
        List<String[]> listString = new ArrayList<String[]>();
        String[] mensagens = null;
        try {
            List<NotaRegistroMedico> list = new NotaRegistroMedico().getNotas(codPaciente);
            if (list == null) {
                mensagens = new String[1];
                mensagens[0] = "Sem Notas!";
                listString.add(mensagens);
            } else {
                for (NotaRegistroMedico item : list) {
                    mensagens = new String[5];
                    mensagens[0] = "sucess";
                    mensagens[1] = item.getDescricao();
                    mensagens[2] = item.getInfoRegistro();
                    mensagens[3] = item.getCodPaciente();
                    mensagens[4] = item.getIdCelular().toString();
                    listString.add(mensagens);
                    item.setSincronizado("S");
                    new NotaRegistroMedico().update(item);
                }
            }
            return listString;
        } catch (Exception e) {
            e.getStackTrace();
            e.printStackTrace();
            mensagens[0] = "Erro ao buscar Notas!";
            listString.add(mensagens);
            return listString;
        }

    }
}
