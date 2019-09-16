//version final//
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {

    private String ip;
    private String puerto;
    private String datos;
    private Socket socket;

    public Cliente(String ip, String puerto, String datos) {
        this.ip = ip;
        this.puerto = puerto;
        this.datos = datos;
        this.socket = null;
    }

    public void IniciarCliente() {
        try {
            File direcion = new File(new File(".").getCanonicalPath() + "\\Downloads");
            EnviarArchivo(direcion);
        } catch (IOException ex) {
            System.out.println("Error en la ruta descarga " + ex);
        }
    }

    public void EnviarArchivo(File direcion) {
        //System.out.println(direcion);
        if (direcion.exists()) {
            socket = null;
            try {
                socket = new Socket(ip, Integer.parseInt(puerto));
            } catch (IOException ex) {
                System.out.println("Error al crear socket " + ex);
            }
            PrintWriter escritor = null;
            try {
                escritor = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException ex) {
                System.out.println("Error al crear escritor " + ex);
            }
            BufferedReader lector = null;
            try {
                lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException ex) {
                System.out.println("Error al crear Lector " + ex);
            }
            String datosEntrada = "";
            long tamar;
            FileOutputStream fos = null;
            BufferedOutputStream bos = null;
            if (datos.equals("fin")) {
                escritor.println("fin");
                //socket.close();
                System.exit(0);
            } else {
                escritor.println(datos);
                try {
                    datosEntrada = lector.readLine();
                } catch (IOException ex) {
                    System.out.println("Error al leer" + ex);
                }
                if (!datosEntrada.equals("non")) {
                    try {
                        File Archivo = new File(datos);
                        tamar = Long.parseLong(datosEntrada);
                        InputStream is = socket.getInputStream();
                        fos = new FileOutputStream(direcion + "\\" + Archivo.getName());
                        bos = new BufferedOutputStream(fos);
                        long count;
                        long c = 0;
                        byte[] buffer = null;
                        if (tamar > 100000000) {
                            buffer = new byte[100000000];
                        } else {
                            if (tamar < 10) {
                                buffer = new byte[(int) tamar];
                            } else {
                                if (tamar <= 100000000) {
                                    buffer = new byte[(int) tamar / 10];

                                }

                            }

                        }
                        long porciento = 0;
                        String barra = "";
                        int l1 = 0, l2 = 0, l3 = 0, l4 = 0, l5 = 0, l6 = 0, l7 = 0, l8 = 0, l9 = 0, l10 = 0;
                        while ((count = is.read(buffer)) > 0) {
                            c = c + count;
                            porciento = (int) (((double) c / tamar) * 100);
                            if ((porciento % 10) > 0) {
                                porciento = porciento + (10 - (porciento % 10));
                            }
                            switch ((int) porciento) {
                                case 10:
                                    l1++;
                                    if (l1 < 2) {
                                        barra = barra + "-";
                                    }

                                    break;
                                case 20:
                                    l2++;
                                    if (l2 < 2) {
                                        barra = barra + "-";
                                    }

                                    break;
                                case 30:
                                    l3++;
                                    if (l3 < 2) {
                                        barra = barra + "-";
                                    }

                                    break;
                                case 40:
                                    l4++;
                                    if (l4 < 2) {
                                        barra = barra + "-";
                                    }

                                    break;
                                case 50:
                                    l5++;
                                    if (l5 < 2) {
                                        barra = barra + "-";
                                    }

                                    break;
                                case 60:
                                    l6++;
                                    if (l6 < 2) {
                                        barra = barra + "-";
                                    }

                                    break;
                                case 70:
                                    l7++;
                                    if (l7 < 2) {
                                        barra = barra + "-";
                                    }

                                    break;
                                case 80:
                                    l8++;
                                    if (l8 < 2) {
                                        barra = barra + "-";
                                    }
                                    break;
                                case 90:
                                    l9++;
                                    if (l9 < 2) {
                                        barra = barra + "-";
                                    }

                                    break;
                                case 100:
                                    l10++;
                                    if (l10 < 2) {
                                        barra = barra + "-";
                                    }

                                    break;
                                default:

                                    break;

                            }
                            fos.write(buffer, 0, (int) count);
                            System.out.print("Descargado: " + porciento + "% [" + barra + "] \r");
                        }
                        if (tamar < 10) {
                            System.out.print("Descargado: " + 100 + "% " + "[----------]" + "\r");
                        }
                        fos.close();
                        fos.flush();
                        socket.close();
                        System.out.println("\n" + "Descarga existosa");
                    } catch (Exception e) {
                        System.out.println("Error al recibir descarga  " + e);
                    }
                } else {
                    System.out.println("no existe");
                    System.exit(0);
                }
            }
        } else {
            System.out.println("ruta no existente");
        }
    }

}
