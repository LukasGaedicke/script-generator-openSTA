package controller;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import utils.OpenFIle;

/**
 *
 * @author lukasgaedicke
 */
public class Controller_Generetor {

    public static ArrayList<ArrayList<String>> listStrings = new ArrayList<ArrayList<String>>();
    public static ArrayList<String> listStrings2 = new ArrayList<String>();
    public static ArrayList<String> fieldNameList = new ArrayList<String>();

    private ArrayList<String> arrayVariaveis = new ArrayList<String>();

    private OpenFIle fIle;
    private OpenFIle fIle2;

    public void generateFile() {
        StringBuilder builder = new StringBuilder();
        //capturar o tamanho total de linhas dos arquivos
        for (int i = 0; i < Controller_Generetor.listStrings.get(0).size(); i++) {
            //percorer por todos os arquivos da lista 
            for (int j = 0; j < Controller_Generetor.listStrings.size(); j++) {
                //capturar o J(largura) da posição i(Profundidade) da lista 
                builder.append(whiteSpace(Controller_Generetor.listStrings.get(j).get(i)));

            }
            Controller_Generetor.listStrings2.add(builder.toString());
            builder.setLength(0);
        }
        writeDatas(Controller_Generetor.listStrings2);
    }

    public void writeDatas(ArrayList<String> arrayList) {
        gerarVariaiveis();
        String filenameVars = JOptionPane.showInputDialog("Enter the variable file name: ");
        fIle = new OpenFIle();
        fIle.writeFile(arrayList, filenameVars);
        fIle2 = new OpenFIle();
        fIle2.writeScript(arrayVariaveis, fieldNameList, filenameVars);
    }

    private String whiteSpace(String a) {
        a = (String.format("%100s", a));
        return a;
    }

    public void gerarVariaiveis() {
        for (int i = 0; i < fieldNameList.size(); i++) {
            arrayVariaveis.add("VAR_" + fieldNameList.get(i));
        }
    }
}
