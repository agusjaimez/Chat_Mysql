/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author agustin
 */
package swi.ng2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Fileprint {

    private String texto;
    private BufferedWriter bw;
    private PrintWriter pw;
    private FileWriter fw;

    public Fileprint(String texto) {
        this.texto = texto;
        this.bw = null;
        this.pw = null;
    }

    public Fileprint() {
    }

    public void setText(String texto) {
        this.texto = texto;
    }

    public void makeFile(String file) {
        File a = new File(file);
        try {
            this.fw = new FileWriter(file);
            this.bw = new BufferedWriter(fw);
            this.pw = new PrintWriter(this.bw);
        } catch (IOException e) {
        }
    }

    public void printFile(String txt) {
        this.pw.println(txt);
    }

    public void closeFile() {
        try {
            if (bw != null) {
                bw.close();
            }
        } catch (IOException e) {
        }
    }}
