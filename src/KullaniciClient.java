import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class KullaniciClient {

    private static InetAddress host;
    private static final int port = 1234;

    public static void main (String[] args){

        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            System.out.println("Host bulunamadi.....");
        }


        Kontrol();
    }

    private static void Kontrol() {

        Socket socket = null;
        Scanner scanner = new Scanner(System.in);
        String girisKontrol;
        String girdi;
        try {
            socket = new Socket(host,port);
            Scanner networkInput = new Scanner(socket.getInputStream());
            PrintWriter networkOutput = new PrintWriter(socket.getOutputStream(),true);
            String durum = "2";
            networkOutput.println(durum);
            while(true) {

                System.out.print("Kullanici Adinizi Giriniz = ");
                String kullaniciAdi = scanner.nextLine();
                System.out.print("Sifrenizi Giriniz = ");
                String parola = scanner.nextLine();
                networkOutput.println(kullaniciAdi);
                networkOutput.println(parola);
                girisKontrol = networkInput.nextLine();
                if (girisKontrol.equals("Basarili")) {
                    System.out.println("Girisiniz Basarili Hosgeldiniz.....");
                    System.out.println("***********************************");
                    break;
                } else {
                    System.out.println("Hatali Kullanici Adi veya Sifre Girdiniz Tekrar Deneyiniz....");
                    System.out.println("**************************************************************");
                }
            }

            while(true) {

                System.out.println("----------------------------------------------");
                System.out.println("Yapmak Istediginiz Islemin Numarasini Giriniz");
                System.out.println("----------------------------------------------");
                System.out.println("1. Makina Listesini Goster");
                System.out.println("2. Makina Raporlarini Goster");
                System.out.println("3. Is Emir Listesini Goster");
                System.out.println("4. Yeni Bir Is Emri Gir");
                System.out.println("5. Programi Kapat");
                System.out.println("----------------------------------------------");
                girdi = scanner.nextLine();
                networkOutput.println(girdi);
                if (girdi.equals("1")) {

                    int listeUzunluk = networkInput.nextInt();
                    networkInput.nextLine();
                    if(listeUzunluk == 0){
                        String yazi = networkInput.nextLine();
                        System.out.println(yazi);
                    }else{
                        for(int i = 0; i < listeUzunluk; i ++){
                            String makinaListe = networkInput.nextLine();
                            System.out.println(makinaListe);
                        }
                    }
                    System.out.println("-------------------------------------------------");
                    System.out.println("Baska Islem Yapmak Istiyor musunuz? (evet-hayir)");
                    String cevap = scanner.nextLine();
                    networkOutput.println(cevap);
                    if(cevap.equals("hayir")){
                        System.out.println("Programdan Cikiliyor..........");
                        break;
                    }else{
                        System.out.println("Ana Islem Menusune Donuluyor...........");
                    }

                }else if(girdi.equals("2")){
                    int makinaUzunluk = networkInput.nextInt();
                    networkInput.nextLine();
                    int raporUzunluk = networkInput.nextInt();
                    networkInput.nextLine();
                    if(makinaUzunluk == 0){
                        String yazi1 = networkInput.nextLine();
                        System.out.println(yazi1);
                    }else{
                        for(int j = 0; j < makinaUzunluk; j++){
                            String makinaListe1 = networkInput.nextLine();
                            System.out.println(makinaListe1);
                        }
                    }
                    System.out.println("---------------------------------------------------");
                    if(raporUzunluk == 0){
                        String yazi2 = networkInput.nextLine();
                        System.out.println(yazi2);
                    }else{
                        for(int k = 0; k < raporUzunluk; k++){
                            String rapor = networkInput.nextLine();
                            System.out.println(rapor);
                        }
                    }
                    System.out.println("-------------------------------------------------");
                    System.out.println("Baska Islem Yapmak Istiyor musunuz? (evet-hayir)");
                    String cevap1 = scanner.nextLine();
                    networkOutput.println(cevap1);
                    if(cevap1.equals("hayir")){
                        System.out.println("Programdan Cikiliyor..........");
                        break;
                    }else{
                        System.out.println("Ana Islem Menusune Donuluyor...........");
                    }

                }else if (girdi.equals("3")){
                    int isUzunluk = networkInput.nextInt();
                    networkInput.nextLine();
                    if(isUzunluk == 0){
                        String isYazi = networkInput.nextLine();
                        System.out.println(isYazi);
                    }else{
                        for(int i = 0; i < isUzunluk; i++){
                            String isEmirListe = networkInput.nextLine();
                            System.out.println(isEmirListe);
                        }
                    }
                    System.out.println("Baska Islem Yapmak Istiyor musunuz? (evet-hayir)");
                    String cevap3 = scanner.nextLine();
                    networkOutput.println(cevap3);
                    if(cevap3.equals("hayir")){
                        System.out.println("Programdan Cikiliyor..........");
                        break;
                    }else{
                        System.out.println("Ana Islem Menusune Donuluyor...........");
                    }
                }else if(girdi.equals("4")){
                    System.out.print("Is Emri Icin Tekil ID giriniz = ");
                    int ID = scanner.nextInt();
                    scanner.nextLine();
                    networkOutput.println(ID);
                    System.out.print("Yapilacak Isin Turunu Giriniz = ");
                    String is = scanner.nextLine();
                    networkOutput.println(is);
                    System.out.print("Yapilacak Isin Birim Cinsinden Uzunlugunu Giriniz = ");
                    int birimUzunluk = scanner.nextInt();
                    scanner.nextLine();//dummy
                    networkOutput.println(birimUzunluk);
                    System.out.println("Is Emriniz Alinmistir......");
                    System.out.println("--------------------------------------------");
                    System.out.println("Baska Islem Yapmak Istiyor musunuz? (evet-hayir)");
                    String cevap2 = scanner.nextLine();
                    networkOutput.println(cevap2);
                    if(cevap2.equals("hayir")){
                        System.out.println("Programdan Cikiliyor..........");
                        break;
                    }else{
                        System.out.println("Ana Islem Menusune Donuluyor...........");
                    }
                }else if(girdi.equals("5")){
                    System.out.println("Program Kapatiliyor..........");
                    break;
                }else{
                    System.out.println("Yanlis Numara Girisi Yaptiniz Tekrar Deneyiniz.........");
                }
            }




        } catch (IOException e) {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                System.out.println("\nBaglanti Kapatiliyor....");
                socket.close();
            }
            catch(IOException ioEx)
            {
                System.out.println("Baglanti Kapatilamadi...");
                System.exit(1);
            }
        }




    }

}
