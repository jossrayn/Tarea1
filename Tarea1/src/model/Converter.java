/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static java.lang.Math.abs;
import java.util.ArrayList;
import model.Data;

/**
 *
 * @author rayn0
 */
public class Converter {
    private String[] binaryRegister = {"0001","0010","0011","0100"};
    private String[] registers = {"ax","bx","cx","dx"};
    
    private String[] binaryOperators = {"001","010","011","100","101"};
    private String[] operators = {"load","store","mov","sub","add"};
    
    private ArrayList<ArrayList<String>> converList; //lista con las informacion del archivo en binario
    
    
    public Converter(){
        converList = new ArrayList<ArrayList<String>>();
    }
    
    
    /*
    entrada: recibe una lista de instrucciones obtenidas del archivo
    salida: lista de sublistas con la informacion convertida en binario
    objetivo: metodo encargado de tomar las instrucciones y convertirlas en binario para la visualizacion en la interfaz
    */
    public ArrayList<ArrayList<String>> convert(ArrayList<String> list,int insNumber){
        for (int i = 0; i<list.size();i++){
            ArrayList<String> temp = new ArrayList<String>(); //sublista temporal que sera insertada en la lista con la convercion
            String line = list.get(i);
            String[] subline = line.split("\\s+|,");//realiza un split en caso de encontrar un espacio o una coma
            if(subline.length == 3){
                String op = getBinary(subline[0],operators,binaryOperators);
                String reg = getBinary(subline[1],registers,binaryRegister);
                String val = converValue(Integer.parseInt(subline[2]));
                temp.add(Integer.toString(insNumber));
                temp.add(op);//obtener operador en binario
                temp.add(op + " " + reg + " " +val);//obtener regitro en binario
                temp.add(val);//obtener valor en binario
                insNumber ++;
            }
            else{
                //obteniendo valores binarios
                String op = getBinary(subline[0],operators,binaryOperators);
                String reg = getBinary(subline[1],registers,binaryRegister);
                temp.add(Integer.toString(insNumber));
                temp.add(op);//obtener operador en binario
                temp.add(op + " " + reg + " 00000000");//obtener regitro en binario
                temp.add("0");//obtener valor en binario
                insNumber ++;//incremento en numero de instruccion para ir a la siguiente
            }
            converList.add(temp);//se añade la instruccion ya traducida
        }
        return converList;
    }
    
    /*
    entrada: recibe un numero entero
    salida: la conversion de ese numero en binario, representado en 8 bits
    objetivo: trasnformar un numero entero en codigo binario string
    */
    private String converValue(int number){
        String value = "";
        String sign = "0";
         if(number>0){
            value = Integer.toBinaryString(number);
         }
        else{
            sign = "1";//en caso de que el valor sea negativo se le asigna un 1 al primer bit
            value = Integer.toBinaryString(abs(number));
        }
        int leftNumbers = 7 - value.length();
        //rellenar de 0 para completar los bits
        for(int i=0 ; i < leftNumbers; i++){
            value = "0"+value;
        }
        value = sign + value;
        return value;
    }
    
    /*
    entrada: recibe el operador que se va a manejar o registro que se esta utilizando
    salida: segun el dato recibido, su valor en codigo binario
    objetivo: recibir codigo de la instruccion que debe ejecutarse y convertirlo en binario
    */
    private String getBinary(String data,String[] temp,String[] list){
        String result = "";
        for (int i = 0 ; i<temp.length ; i++){
            if (temp[i].equals(data.toLowerCase().toString())){
                result = list[i];
            }
        }
        return result;
    }
    
    /*
    entrada: recibe el string de la instruccion que se esta ejecutando
    salida: valores de los registros ax,bx,cx,dx modificados(de ser necesario) y el valor del acumulador
    objetivo: interpretar las instrucciones recibidas para modificar los valores necesarios.
    */
    public void intepreter(String instruction, Data obj){
        String[] subinst = instruction.split("\\s+|,");//realiza un split en caso de encontrar un espacio o una coma
        if (subinst.length == 3){
            switch(subinst[1].toLowerCase()){
                case "ax":
                    obj.setAx(Integer.parseInt(subinst[2]));
                    break;
                case "bx":
                    obj.setBx(Integer.parseInt(subinst[2]));
                    break;
                case "cx":
                    obj.setCx(Integer.parseInt(subinst[2]));
                    break;
                case "dx":
                    obj.setDx(Integer.parseInt(subinst[2]));
                    break;
            }          
        }
        else{
            //switch en las operaciones
            switch(subinst[0].toLowerCase()){
                case "store":
                    //switch en los registros
                    switch(subinst[1].toLowerCase()){
                        case "ax":
                            obj.setAx(obj.getAc());
                            break;
                        case "bx":
                            obj.setBx(obj.getAc());
                            break;
                        case "cx":
                            obj.setCx(obj.getAc());
                            break;
                        case "dx":
                            obj.setDx(obj.getAc());
                            break;
                    }
                    break;
                case "load":
                    switch(subinst[1].toLowerCase()){
                        case "ax":
                            obj.setAc(obj.getAx());
                            break;
                        case "bx":
                            obj.setAc(obj.getBx());
                            break;
                        case "cx":
                            obj.setAc(obj.getCx());
                            break;
                        case "dx":
                            obj.setAc(obj.getDx());
                            break;
                    }
                    break;
                case "add":
                    switch(subinst[1].toLowerCase()){
                        case "ax":
                            obj.setAc(obj.getAc()+obj.getAx());
                            break;
                        case "bx":
                            obj.setAc(obj.getAc()+obj.getBx());
                            break;
                        case "cx":
                            obj.setAc(obj.getAc()+obj.getCx());
                            break;
                        case "dx":
                            obj.setAc(obj.getAc()+obj.getDx());
                            break;
                    }
                    break;
                case "sub":
                    switch(subinst[1].toLowerCase()){
                        case "ax":
                            obj.setAc(obj.getAc()-obj.getAx());
                            break;
                        case "bx":
                            obj.setAc(obj.getAc()-obj.getBx());
                            break;
                        case "cx":
                            obj.setAc(obj.getAc()-obj.getCx());
                            break;
                        case "dx":
                            obj.setAc(obj.getAc()-obj.getDx());
                            break;
                    }
                    break;
            }
        }
    }
}
