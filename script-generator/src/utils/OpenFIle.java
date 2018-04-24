/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lukasgaedicke
 */
public class OpenFIle {

    public ArrayList readFile(String file) {
        ArrayList<String> arrayList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                arrayList.add(sCurrentLine);
            }

        } catch (IOException e) {
            return null;
        }
        return arrayList;
    }

    public void writeFile(ArrayList<String> list, String fileName) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fileName + ".fvr"), "utf-8"))) {
            for (int i = 0; i < list.size(); i++) {
                writer.write(list.get(i) + "\n");
            }
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(OpenFIle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void writeScript(ArrayList<String> listVar, ArrayList<String> listCampos, String fileName) {

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fileName + "-script.txt"), "utf-8"))) {
            writer.write("!VARIABLES \n\n");
            //inserir as vars
            for (int i = 0; i < listVar.size(); i++) {
                writer.write("CHARACTER*100 " + listVar.get(i) + "\n");
            }
            //variavel do arquivo
            writer.write("CHARACTER*" + listVar.size() * 100 + " VAR_FILE, FILE = \"" + fileName + "\" , SCRIPT\n\n\n");
            //MUTEX
            writer.write("!MUTEX\n\n");
            writer.write("Acquire LOCAL Mutex \"LOCAL-MUTEX\"\n");
            writer.write("next VAR_FILE\n");
            for (int i = 0; i < listVar.size(); i++) {
                writer.write("SET " + listVar.get(i) + "= ~EXTRACT(" + i * 100 + "," + (i + 1) * 100 + ",VAR_FILE) \n");
            }
            writer.write("Release LOCAL Mutex \"LOCAL-MUTEX\"\n\n\n");

            writer.write("!REQUEST POST\n");
            StringBuilder builder = new StringBuilder();
            builder.append("\"");
            for (int i = 0; i < listCampos.size(); i++) {

                if (listCampos.size() == i + 1) {
                    builder.append(listCampos.get(i) + "=\"+" + listVar.get(i) + "+\"\"");
                } else {
                    builder.append(listCampos.get(i) + "=\"+" + listVar.get(i) + "+\"&");
                }
            }
            writer.write(builder.toString());
            writer.close();
        } catch (IOException ex) {

        }
    }
}
