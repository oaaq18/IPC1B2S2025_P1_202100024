package com.mycompany.ipc1b_2s2025;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class IPC1B_2S2025 {
private static Scanner sc = new Scanner(System.in);
//variables
private static boolean valido = false;// variable para verficar el ingreso adecuado de datos
private static String[] NombreProducto= new String[100];
private static int[] CategoriaProducto= new int[100];
private static double[] PrecioProducto= new double[100];
private static int[] StockProducto = new int[100];
private static int ContadorProductos=0;
private static boolean[] ProductosActivos= new boolean[100]; //varificador logico


private static int[] ID= new int[100];

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
                AgregarProdcuto();
            break;
            case 2:
                System.out.println(">>> Buscar Producto <<<");
                // Aquí va la lógica de buscar
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
    private static void AgregarProdcuto(){
        valido=false;
        String Nombre;
        double Precio;
        int Categoria;
        int Stok;
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
        
        
    }
}
