package com.mycompany.ipc1b_2s2025;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class IPC1B_2S2025 {
private static Scanner sc = new Scanner(System.in);//variable scanner
private static Random rand=new Random();
//variables
private static boolean valido = false;// variable para verficar el ingreso adecuado de datos
private static String[] NombreProducto= new String[100];
private static String[] CategoriasProducto = {"Camisas", "Pantalones", "Accesorios", "Otros"}; 
private static double[] PrecioProducto= new double[100];
private static int[] StockProducto = new int[100];
private static String[] CodigoProducto = new String[100];
private static int ContadorProductos=0;
private static boolean[] ProductosActivos= new boolean[100]; //varificador logico


    public static void main(String[] args) {
        Menu();
    }
    
    private static void Menu(){
        int opcion=8;
        do {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Agregar Producto");
        System.out.println("2. Buscar Producto");
        System.out.println("3. Eliminar Producto");
        System.out.println("4. Registrar Venta");
        System.out.println("5. Generar Reportes");
        System.out.println("6. Ver Datos del Estudiante");
        System.out.println("7. Bitácora");
        System.out.println("8. Salir");
    
        //validar que el valor sea entero
        while(!valido){
            try{
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            valido=true;
            } catch (InputMismatchException e){
                System.out.println("ERROR: debe ingresar un numero entero");
                sc.nextLine(); // limpiar buffer
            }
        }
        sc.nextLine();
        //resgresa el valor natural de la validacion
        //fin validacion
        switch (opcion) {
            case 1:
                System.out.println("ingresando a agregar producto...\n");
                AgregarProducuto();
            break;
            case 2:
                System.out.println("ingresando a buscar producto...\n");
                BuscarProducto();
            break;
            case 3:
                System.out.println(">>> Eliminar Producto <<<");
                // Aquí va la lógica de eliminar
            break;
            case 4:
                System.out.println(">>> Registrar Venta <<<");
                // Aquí va la lógica de registrar una venta
            break;
            case 5:
                System.out.println(">>> Generar Reportes <<<");
                // Aquí va la lógica de generar reportes
            break;
            case 6:
                System.out.println(">>> Ver Datos del Estudiante <<<");
                // Aquí puedes mostrar tus datos: nombre, carné, curso, etc.
            break;
            case 7:
                System.out.println(">>> Bitácora <<<");
                // Aquí puedes imprimir un historial de acciones
            break;
            case 8:
                System.out.println("Saliendo del sistema...");
                break;
            default:
            System.out.println("Opción no válida.");
        }

    } while (opcion != 8);

    }
    //inicio op1 agregar producto
    private static void AgregarProducuto(){
        valido=false;
        int Aleatorio = rand.nextInt(100);
        String Nombre;
        double Precio=0;
        int Categoria=4;
        int Stok=0;
        String Codigo;
    
        if(ContadorProductos>=100){
            System.out.println("ERROR: Limite de productos alcanzado");
            return;
        }
        
        //ingreso de nombre
        System.out.println("Ingrese el nombre del producto");
        Nombre = sc.nextLine();
        //fin ingreso nombre
        
        //ingreso de categoria
        
        while(!valido){
            System.out.println("1.Camisas");
            System.out.println("2.Pantalones");
            System.out.println("3.Accesorios");
            System.out.println("4.Otros");
            
            try{
            System.out.println("Ingrese la categoria del producto");
            Categoria= sc.nextInt();
            if(Categoria<0 || Categoria>=5){
                System.out.println("ERROR: opcion fuera de rango");
            }else{
            valido=true;
            }
            
            } catch (InputMismatchException e){
                System.out.println("ERROR: debe ingresar valor entero");
                sc.nextLine(); // limpiar buffer
            }
        }
        valido=false;//resgresa el valor natural de la validacion
        //fin ingreso categoria
        
        //ingreso de precio
        
        while(!valido){
            try{
            System.out.println("Ingrese el precio del producto");
            Precio= sc.nextDouble();
            if(Precio<0){
                System.out.println("ERROR: El precio no puede ser menor que 0");
            }else{
            valido=true;
            }
            } catch (InputMismatchException e){
                System.out.println("ERROR: debe ingresar numerico");
                sc.nextLine(); // limpiar buffer
            }
        }
        valido=false;//resgresa el valor natural de la validacion
        //fin ingreso precio
        //ingreso de stock
        
        while(!valido){
            try{
            System.out.println("Ingrese la cantidad en stock");
            Stok= sc.nextInt();
            if(Stok<=0){
                System.out.println("ERROR: El precio no puede ser menor o igual a 0");
            }else{
            valido=true;
            }
            } catch (InputMismatchException e){
                System.out.println("ERROR: debe ingresar numerico");
                sc.nextLine(); // limpiar buffer
            }
        }
        //fin ingreso stock
        //ingreso de datos a los vectores
        NombreProducto[ContadorProductos]=Nombre;
        CategoriasProducto[ContadorProductos]=CategoriasProducto[Categoria+1];
        PrecioProducto[ContadorProductos]=Precio;
        StockProducto[ContadorProductos]=Stok;
        ProductosActivos[ContadorProductos]=true;
        //seleccionar codigo para categoria
        
         switch (Categoria) {
             case 1:
                   CodigoProducto[ContadorProductos]="CA"+ContadorProductos+"-"+Aleatorio;  
             break;
             case 2:
                 CodigoProducto[ContadorProductos]="PA"+ContadorProductos+"-"+Aleatorio;
             break;
             case 3:
                 CodigoProducto[ContadorProductos]="AC"+ContadorProductos+"-"+Aleatorio;
             break;
             case 4:
                 CodigoProducto[ContadorProductos]="OT"+ContadorProductos+"-"+Aleatorio;
             break;
         }
         System.out.println("Producto ingresado con exito:");
         System.out.println("Nombre: "+NombreProducto[ContadorProductos]);
         System.out.println("Categoria: "+CategoriasProducto[ContadorProductos]);
         System.out.println("Precio: "+ PrecioProducto[ContadorProductos]);
         System.out.println("Codigo: "+CodigoProducto[ContadorProductos]);
         ContadorProductos++;
        return;
        
    }
    //fin ingreso producto
    //inicio buscar producto
    private static void BuscarProducto(){
        int opcion=0;//variable para escojer la rubrica en que buscar
        String busqueda;// varibale para buscar por nombre
        boolean econtrado=false;// variable para verificar si fue econtrado
        System.out.println("Buscar por: ");
        System.out.println("1.Nombre");
        System.out.println("2.Codigo");
        System.out.println("3.Categoria");
        
        while(!valido){
            try{
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            valido=true;
            } catch (InputMismatchException e){
                System.out.println("ERROR: debe ingresar un numero entero");
                sc.nextLine(); // limpiar buffer
            }
        }
        
        switch (opcion){
            case 1:
                System.out.println("Ingrese el nombre a buscar:");
                busqueda = sc.nextLine().toLowerCase();
                
                for(int i=0;i<=ContadorProductos;i++){
                    if (busqueda!=NombreProducto[i]) {
                        econtrado=false;
                    }else{
                        System.out.println("---PRODUCTO ECONTRADO:---");
                        System.out.println("Nombre: "+NombreProducto[i]);
                        System.out.println("Precio: "+PrecioProducto[i]);
                        System.out.println("Codigo: "+CodigoProducto[i]);
                        System.out.println("Categoria: "+CategoriasProducto[i]);
                        econtrado=true;
                    }  
                }
                if (!econtrado) {
                    System.out.println("Producto no econtrado");
                }
            break;
            case 2:
            break;
            case 3:
            break;
            default:
                System.out.println("ERROR:opcion fuera de rango");
        }
                
    }
    //fin buscar producto
}
