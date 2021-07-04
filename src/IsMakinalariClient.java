import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class IsMakinalariClient {

    private static InetAddress host;
    private static final int port = 1234;

    public static void main (String [] args){

        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            System.out.println("Host Bulunamadi....");
        }
        BilgileriYolla();


    }

    private static void BilgileriYolla() {

        Socket socket = null;
        Scanner scanner = new Scanner(System.in);



        try {
            socket = new Socket(host,port);
            Scanner inputClient = new Scanner(socket.getInputStream());
            PrintWriter outputClient = new PrintWriter(socket.getOutputStream(),true);
            String durum = "1";
            outputClient.println(durum);
            System.out.print("Makina Adi Giriniz = ");
            String makinaAd = scanner.nextLine();
            System.out.print("Makina Turunu Giriniz = ");
            String makinaTur = scanner.nextLine();
            System.out.print("Makina Hizini Dakika cinsinden Birim Ise Gore Giriniz = ");
            int makinaHiz = scanner.nextInt();
            scanner.nextLine();
            String makinaDurum = "EMPTY";
            outputClient.println(makinaAd);
            outputClient.println(makinaTur);
            outputClient.println(makinaDurum);
            outputClient.println(makinaHiz);

            int Id = inputClient.nextInt();
            inputClient.nextLine();
            String tur, isdurum;
            int birim, isId;

            while(true){

                isdurum = inputClient.nextLine();
                tur = inputClient.nextLine();
                birim = inputClient.nextInt();
                inputClient.nextLine();
                isId = inputClient.nextInt();
                inputClient.nextLine();


                if(tur.equals(makinaTur) && isdurum.equals("EMPTY")){

                    String girdi = "Girdi";
                    outputClient.println(girdi);
                    System.out.println("Makina Calismaya Basliyor. Tahmini Bitirme suresi = " + (birim * makinaHiz) + " dakikadir...");
                    int toplam = birim * makinaHiz;
                    String calismaDurum = "BUSY";
                    outputClient.println(calismaDurum);
                    try {
                        Thread.sleep(toplam * 100);
                        String rapor;
                        System.out.println(Id + ". Makina calismasini " + (birim * makinaHiz) + " dakika surede bitirmistir....");
                        rapor = Id + ". Makina Istenen " + birim + " birim " + tur + " Is Uzerindeki Calismasini " + (birim * makinaHiz) + " dakika surede bitirmistir....";
                        outputClient.println(rapor);
                        calismaDurum = "EMPTY";
                        outputClient.println(calismaDurum);
                        outputClient.println(isId);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }else{
                    String girmedi = "Girmedi";
                    outputClient.println(girmedi);
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                System.out.println("\nClosing connection...");
                socket.close();
            }
            catch(IOException ioEx)
            {
                System.out.println("Unable to disconnect!");
                System.exit(1);
            }
        }


    }
}
