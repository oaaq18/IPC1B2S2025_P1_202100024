package com.mycompany.ipc1b_2s2025;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;

public class IPC1B_2S2025 {
private static Scanner sc = new Scanner(System.in);//variable scanner
private static Random rand=new Random();
//variables de producto
private static boolean valido = false;// variable para verficar el ingreso adecuado de datos
private static String[] NombreProducto= new String[100];
private static String[] CategoriaAsignada = new String[100]; // categoría real de cada producto
private static String[] CategoriasProducto = {"Camisas", "Pantalones", "Accesorios", "Otros"}; 
private static double[] PrecioProducto= new double[100];
private static int[] StockProducto = new int[100];
private static String[] CodigoProducto = new String[100];
private static int ContadorProductos=0;
private static boolean[] ProductosActivos= new boolean[100]; //varificador logico
//variables de venta
private static int[] NoFactura=new int[100];//almacena el No. de la factura
private static String[][] CodigosFactura= new String[100][100];//almacena el id de la factura y los codigos de la factura en esa posicion
private static String[][] DescripcionFactura= new String[100][100];//almacena el id de la factura y la descripcion de los productos de la factura en esa posicion
private static int[][] CantidadFactura= new int[100][100]; ////almacena el cantidad de los productos de la factura
private static double[] TotalFactura = new double[100];// almacena el total de la factura
private static String[] FechaYHoraFactura = new String[100]; //almacena la fecha y hora de la factura
private static int[][]  ProductosEnFactura= new int[100][100];//almacena la cantidad de productos en una factura para mostrarlos en los ciclos for
private static int ContadorFactura=0; //correlativo de facturas
//variable de bitacora
private static String[] Bitacora=new String[1000];
private static int ContadorBitacora=0;

     public static void main(String[] args) {
        Menu();
    }
    //-----------------------metodos ---------------------------
    //metodo para validar lectura de entero
    private static int leerEntero(String mensaje){
        while(true){
        try{
            System.out.println(mensaje);
            int valor=sc.nextInt();
            sc.nextLine();
            return valor;
        }catch(InputMismatchException e){
            System.out.println("ERROR: Ingrese un numero entero");
            sc.nextLine();
        }
        }
    }
    //fin metodo
    //metodo para validar lectura de double
    private static double leerDouble(String mensaje) {
        while (true) {
            try {
              System.out.print(mensaje);
              double valor = sc.nextDouble();
              sc.nextLine();
              return valor;
         } catch (InputMismatchException e) {
            System.out.println("ERROR: ingrese un número decimal válido.");
            sc.nextLine();
        }
     }
    }
    
    private static void mostrarProducto(int i) {
        System.out.println("------");
        System.out.println("Nombre: " + NombreProducto[i]);
        System.out.println("Precio: " + PrecioProducto[i]);
        System.out.println("Cantidad en stock: "+ StockProducto[i]);
        System.out.println("Codigo: " + CodigoProducto[i]);
        System.out.println("Categoria: " + CategoriaAsignada[i]);
    }
    private static int buscarProductoPorcodigo(String codigo){
        for (int i = 0; i < ContadorProductos; i++) {
            if (codigo.equalsIgnoreCase(CodigoProducto[i]) && ProductosActivos[i] ) {
              return i;  
            }
        }
        return -1;// no se econtro
    }
    //------------------------fin metodos---------------------
    private static void Menu(){
       // sc.nextLine();
        int opcion=8;
        do {
            System.out.println(" "+ContadorProductos);    
        System.out.println("\n--- MENU ---");
        System.out.println("1. Agregar Producto");
        System.out.println("2. Buscar Producto");
        System.out.println("3. Eliminar Producto");
        System.out.println("4. Registrar Venta");
        System.out.println("5. Generar Reportes");
        System.out.println("6. Ver Datos del Estudiante");
        System.out.println("7. Bitácora");
        System.out.println("8. Salir");
        System.out.println("9. Cargar 20 productos de testeo");
        opcion= leerEntero("Ingrese la opcion ");
  
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
                System.out.println("ingresando a eliminar producto...\n");
                eliminarProducto();
            break;
            case 4:
                System.out.println("ingresando a registrar venta...\n");
                 registrarVenta();
              
            break;
            case 5:
                System.out.println("ingresando a generar reporte...\n");
                GenerarReporte();
            break;
            case 6:
                System.out.println("--DATOS DE ESTUDIANTE--");
                System.out.println("Nombre: Osmar Alejandro Alay Quevedo");
                System.out.println("Carnet: 202100024");
                
            break;
            case 7:
                System.out.println("ingresando a bitacora...\n");
                bitacora();
            break;
            case 8:
                System.out.println("Saliendo del sistema...");
                break;
            case 9://opcion para ingresar 20 productos testeados
                cargarProductosTest();
                break;
            default:
            System.out.println("Opción no válida.");
        }

    } while (opcion != 8);

    }
    //inicio op1 agregar producto
    private static void AgregarProducuto(){
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        //valido=false;
        int Aleatorio = rand.nextInt(100);
        String Nombre;
        double Precio=0;
        int Categoria=4;
        int Stok=0;
        if(ContadorProductos>=100){
            System.out.println("ERROR: Limite de productos alcanzado");
            Bitacora[ContadorBitacora]="|"+ahora.format(formato)+" |ACCION: "+"limite de productos alcanzado"+" |ACCION INCORRECTA"+" |ADMIN";
            ContadorBitacora++;
            return;
        }
        
        //ingreso de nombre
        System.out.println("Ingrese el nombre del producto");
        Nombre = sc.nextLine();
        //fin ingreso nombre
        
        //ingreso de categoria
        do {
            System.out.println("1.Camisas");
            System.out.println("2.Pantalones");
            System.out.println("3.Accesorios");
            System.out.println("4.Otros");
            Categoria= leerEntero("Ingrese la categoria del producto");
           if(Categoria<=0 || Categoria>=5){
                System.out.println("ERROR: opcion fuera de rango");
            }
            
        } while (Categoria<1 || Categoria>4);
        //ingreso de precio
        do {
            Precio=leerDouble("Ingrese el precio del producto: ");
            if(Precio<=0){
                System.out.println("ERROR: El precio no puede ser menor o igual a 0");
            }
        } while (Precio<=0);
        //fin ingreso precio
        //ingreso de stock
        do {
            Stok= leerEntero("Ingrese la cantidad en stock del producto");
            if(Stok<=0){
                System.out.println("ERROR: El stock no puede ser menor o igual a 0");
            }
        } while (Stok<=0);
        //fin ingreso stock
        //ingreso de datos a los vectores
        NombreProducto[ContadorProductos]=Nombre;
        CategoriaAsignada[ContadorProductos] = CategoriasProducto[Categoria - 1];
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
         System.out.println("Categoria: "+CategoriaAsignada[ContadorProductos]);
         System.out.println("Precio: "+ PrecioProducto[ContadorProductos]);
         System.out.println("Codigo: "+CodigoProducto[ContadorProductos]);
         ContadorProductos++;
         Bitacora[ContadorBitacora]="|"+ahora.format(formato)+" |ACCION: "+"Producto ingresado"+" |ACCION CORRECTA"+" |ADMIN";
            ContadorBitacora++;
    }
    //fin ingreso producto
    
    //op 2 inicio buscar producto
    private static void BuscarProducto(){
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        valido=false;
        int opcion=0;//variable para escojer la rubrica en que buscar
        String busqueda;// varibale para buscar por nombre
        int BusquedaCategoria;
        boolean econtrado=false;// variable para verificar si fue econtrado
        if (ContadorProductos<=0) {//verificador de productos
            System.out.println("ERROR: no hay productos ingresados");
            Bitacora[ContadorBitacora]="|"+ahora.format(formato)+" |ACCION: "+"se intento ingresar a busqueda sin existencias"+" |ACCION INCORRECTA"+" |ADMIN";
            ContadorBitacora++;
            return;
        }
        System.out.println("Buscar por: ");
        System.out.println("1.Nombre");
        System.out.println("2.Codigo");
        System.out.println("3.Categoria");
        opcion=leerEntero("Ingrese una opcion: ");
        
        switch (opcion){
            case 1://buscar por nombre
                System.out.println("Ingrese el nombre a buscar:");
                busqueda = sc.nextLine();
                for(int i=0;i<ContadorProductos;i++){
                    if (busqueda.equalsIgnoreCase(NombreProducto[i]) && ProductosActivos[i]==true) {
                        mostrarProducto(i);
                        econtrado=true;
                    }  
                }
                if (!econtrado) {
                    System.out.println("Producto no econtrado");
                }
                Bitacora[ContadorBitacora]="|"+ahora.format(formato)+" |ACCION: "+"se ingreso busco productos por nombre"+" |ACCION CORRECTA"+" |ADMIN";
                ContadorBitacora++;
            break;
            case 2://buscar por codigo
                int BusquedaCodigo;
                System.out.println("Ingrese el codigo del producto a buscar:");
                busqueda = sc.nextLine();
                BusquedaCodigo=buscarProductoPorcodigo(busqueda);
                if (BusquedaCodigo!=-1) {
                    mostrarProducto(BusquedaCodigo);
                    
                }else{
                System.out.println("Producto no econtrado");
                }
               Bitacora[ContadorBitacora]="|"+ahora.format(formato)+" |ACCION: "+"se busco un producto por nombre"+" |ACCION CORRECTA"+" |ADMIN";
               ContadorBitacora++;
            break;
            case 3://buscar por categoria
                System.out.println("1.Camisas");
                System.out.println("2.Pantalones");
                System.out.println("3.Accesorios");
                System.out.println("4.Otros");
                do {
                  BusquedaCategoria = leerEntero("Ingrese el numero de la categoria a buscar:");
                    if (BusquedaCategoria<=0 || BusquedaCategoria>=5) {
                        System.out.println("ERROR: opcion fuera de rango");
                    }
                  
                } while (BusquedaCategoria<=0 || BusquedaCategoria>=4);
                
                for(int i=0;i<=ContadorProductos;i++){
                    if (CategoriaAsignada[BusquedaCategoria-1]!=CategoriaAsignada[i] && ProductosActivos[i]==true) {
                        econtrado=false;
                    }else{
                        mostrarProducto(i);
                        econtrado=true;
                    }  
                }
                if (!econtrado) {
                    System.out.println("Producto no econtrado");
                }
                Bitacora[ContadorBitacora]="|"+ahora.format(formato)+" |ACCION: "+"se busco un producto codigo"+" |ACCION CORRECTA"+" |ADMIN";
               ContadorBitacora++;
            break;
            default:
                System.out.println("ERROR:opcion fuera de rango");
        }
                
    }
    //fin buscar producto
    //op 3 eliminar producto
    private static void eliminarProducto(){
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        
        String busqueda;
        int opcion;
        boolean encontrado=false;
        if (ContadorProductos<=0) {//verificador de productos
            System.out.println("ERROR: no hay productos ingresados");
            Bitacora[ContadorBitacora]="|"+ahora.format(formato)+" |ACCION: "+"se intento eliminar productos sin existencia"+" |ACCION INCORRECTA"+" |ADMIN";
            ContadorBitacora++;
            return;
        }
        
        int BusquedaCodigo;
                System.out.println("Ingrese el codigo del producto a buscar:");
                busqueda = sc.nextLine();
                BusquedaCodigo=buscarProductoPorcodigo(busqueda);
                if (BusquedaCodigo!=-1) {
                    mostrarProducto(BusquedaCodigo);
                    
                    do{
                    opcion = leerEntero("desea eliminar? 1: si 0: no: ");
                    if (opcion==1) {
                        ProductosActivos[BusquedaCodigo]=false;
                        ContadorProductos--;
                        System.out.println("PRODUCTO ELIMINADO CON EXITO");
                        Bitacora[ContadorBitacora]="|"+ahora.format(formato)+" |ACCION: "+"se elimino un producto"+" |ACCION CORRECTA"+" |ADMIN";
                        ContadorBitacora++;
                        break;
                    }else if(opcion==0){
                        System.out.println("ELIMINACION ABORTADA");
                        Bitacora[ContadorBitacora]="|"+ahora.format(formato)+" |ACCION: "+"se cancelo una eliminacion"+" |ACCION CORRECTA"+" |ADMIN";
                        ContadorBitacora++;
                        break;
                    }else{
                        System.out.println("ERROR: opcion fuera de rango");
                    }
                }while(opcion !=0 || opcion !=1);
                }else{
                System.out.println("Producto no econtrado");
                }
                
        
    }
    //fij op 3
    //op 4 generar venta
    private static void registrarVenta(){
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        
        int continuar;
        int Diferecia; // muestra la diferecia en stock para que sea posible vender
        int CantidadVenta=0;
        int ContadorEnFactura=0; //lleva el conteo de los productos ingresados en la factura
        boolean encontrado=false;
        boolean CantidadValida=false; //verifica que la cantidad ingresada sea correcta
        String busqueda;
        if (ContadorProductos<=0) {//verificador de productos
            System.out.println("ERROR: no hay productos ingresados");
            Bitacora[ContadorBitacora]="|"+ahora.format(formato)+" |ACCION: "+"se inento realizar venta sin productos"+" |ACCION INCORRECTA"+" |ADMIN";
            ContadorBitacora++;
            return;
        }
        do {
        
            //ingresando producto
            do {
                int BusquedaCodigo;
                System.out.println("Ingrese el codigo del producto a buscar:");
                busqueda = sc.nextLine();
                BusquedaCodigo=buscarProductoPorcodigo(busqueda);
                
                if(BusquedaCodigo == -1) {
                    System.out.println("ERROR: Producto no econtrado");
                    encontrado=false;
                }else if(StockProducto[BusquedaCodigo]<=0){
                    System.out.println("ERROR: no hay suficiente producto en stock");
                    Bitacora[ContadorBitacora]="|"+ahora.format(formato)+" |ACCION: "+"se intento realizar una venta sin stock disponinble"+" |ACCION CORRECTA"+" |ADMIN";
                    ContadorBitacora++;
                    encontrado=false;
                }else{
                    System.out.println("PRODUCTO ECONTRADO:");
                    System.out.println("Nombre: "+NombreProducto[BusquedaCodigo]);
                    System.out.println("Precio: "+PrecioProducto[BusquedaCodigo]);
                    
                    do {
                        System.out.println("Ingrese la cantidad a vender");
                        CantidadVenta=leerEntero(busqueda);
                        if (CantidadVenta>StockProducto[BusquedaCodigo]) {
                            System.out.println("ERROR: la cantidad ingresada es mayor a la cantidad en stock");
                            Diferecia=StockProducto[BusquedaCodigo]-CantidadVenta;
                            System.out.println("Ingrese una cantidad menor o igual a: "+Diferecia);
                            CantidadValida=false;
                        }else{
                        CantidadValida=true;
                        }
                    } while (!CantidadValida);
                    //ingrsando el producto a los vectores
                    
                    CodigosFactura[ContadorFactura][ContadorEnFactura]=busqueda;
                    DescripcionFactura[ContadorFactura][ContadorEnFactura]=NombreProducto[BusquedaCodigo];
                    CantidadFactura[ContadorFactura][ContadorEnFactura]=CantidadVenta;
                    TotalFactura[ContadorFactura]=TotalFactura[ContadorFactura]+PrecioProducto[BusquedaCodigo]*CantidadVenta;
                    StockProducto[BusquedaCodigo]=StockProducto[BusquedaCodigo]-CantidadVenta;
                    
                    ProductosEnFactura[ContadorFactura][ContadorEnFactura]++;
                    ContadorEnFactura++;
                    //finingreso
                    encontrado=true;
                }
            } while (!encontrado);

            do {
                continuar= leerEntero("Desea ingresar otro producto? 1: si 0: No");
                if (continuar!=1 && continuar!=0) {
                    System.out.println("Opcion fuera de rango");
                }
            } while (continuar!=1 && continuar!=0);
            
        } while (continuar!=0);
        FechaYHoraFactura[ContadorFactura]=ahora.format(formato);
        ContadorFactura++;
        System.out.println("--Factura registrada--");
        Bitacora[ContadorBitacora]="|"+ahora.format(formato)+" |ACCION: "+"se registro una factura"+" |ACCION CORRECTA"+" |ADMIN";
        ContadorBitacora++;
    }
    //fin op 4
    //op 5 generar reporte
    private static void GenerarReporte(){
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        int opcion;
         if (ContadorProductos<=0) {//verificador de productos
            System.out.println("ERROR: no hay productos ni ventas ingresados");
            return;
        }
        System.out.println("--REPORTES--");
        System.out.println("1. Productos");
        System.out.println("2. Ventas");
        opcion = leerEntero("Ingrese una opcion: ");
        switch(opcion){
            case 1:
                String ProductosPDF= ahora.format(formato)+"_stock.pdf";
                //inicio documento reporte
                
                //fin documento reporte
                for (int i = 0; i < ContadorProductos; i++) {
                 if (ProductosActivos[i]==false) {
               
                  }else{
                    System.out.println("----------------------------");
                    mostrarProducto(i);
                    System.out.println("----------------------------");
                 }
            }
                Bitacora[ContadorBitacora]="|"+ahora.format(formato)+" |ACCION: se realizo un reporte de productos |ACCION CORRECTA"+" |ADMIN";
                ContadorBitacora++;
            break;
            case 2:
                if (ContadorFactura<=0) {
                    System.out.println("EEROR: No hay facturas registradas");
                    return;
                }
                for (int i = 0; i < ContadorFactura; i++) {
                    System.out.println("-------------------------");
                    System.out.println("FACTURA No:"+NoFactura[i]);
                    System.out.println("Fecha: "+FechaYHoraFactura[i]);
                    System.out.println("Total: "+TotalFactura[i]);
                    System.out.println("Productos: ");
                    for (int j = 0; j <= ProductosEnFactura[i][j]; j++) {
                        System.out.println("Codigo: "+CodigosFactura[i][j]);
                        System.out.println("Descripcion: "+DescripcionFactura[i][j]);
                    }
                    
                }
                Bitacora[ContadorBitacora]="|"+ahora.format(formato)+" |ACCION: se realizo un reporte de facturas |ACCION CORRECTA |ADMIN";
                ContadorBitacora++;
            break;
        }
        
    }
    //fin op 5
    // op 7 bitacora
    private static void bitacora(){
        for (int i = 0; i <= ContadorBitacora; i++) {
            System.out.println(""+Bitacora[i]);
        }
    }
    // MÉTODO PARA CARGAR 20 PRODUCTOS DE PRUEBA
    private static void cargarProductosTest() {
        if (ContadorProductos + 20 > 100) {
            System.out.println("ERROR: No hay espacio suficiente para cargar los productos de prueba.");
            return;
        }

        for (int i = 0; i < 20; i++) {
            int Aleatorio = rand.nextInt(100); // número aleatorio
            int Categoria = (i % 3)+1;       // alterna entre 1 y 4
            String Nombre = "ProductoTest" + (ContadorProductos + 1);
            double Precio = 10 + rand.nextInt(90) + rand.nextDouble(); // precio entre 10 y 100
            int Stock = rand.nextInt(50) + 1; // stock entre 1 y 50

            // Guardar en los vectores
            NombreProducto[ContadorProductos] = Nombre;
            CategoriaAsignada[ContadorProductos] = CategoriasProducto[Categoria - 1];
            PrecioProducto[ContadorProductos] = Precio;
            StockProducto[ContadorProductos] = Stock;
            ProductosActivos[ContadorProductos] = true;

            // Generar código según categoría
            switch (Categoria) {
                case 1:
                    CodigoProducto[ContadorProductos] = "CA" + ContadorProductos + "-" + Aleatorio;
                    break;
                case 2:
                    CodigoProducto[ContadorProductos] = "PA" + ContadorProductos + "-" + Aleatorio;
                    break;
                case 3:
                    CodigoProducto[ContadorProductos] = "AC" + ContadorProductos + "-" + Aleatorio;
                    break;
                case 4:
                    CodigoProducto[ContadorProductos] = "OT" + ContadorProductos + "-" + Aleatorio;
                    break;
            }

            ContadorProductos++;
        }

        System.out.println("Se cargaron 20 productos de prueba exitosamente.");
        }
    
    }






