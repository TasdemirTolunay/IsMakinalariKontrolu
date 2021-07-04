import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class MerkeziSunucu {

    private static ServerSocket serverSocket;
    private static final int port = 1234;
    static ArrayList<String> ad = new ArrayList<String>();
    static ArrayList<String> tur = new ArrayList<String>();
    static ArrayList<Integer> hiz = new ArrayList<Integer>();
    static ArrayList<String> durum = new ArrayList<String>();
    static ArrayList<Integer> id = new ArrayList<Integer>();
    static ArrayList<String> rapor = new ArrayList<String>();
    static ArrayList<String> kullanici = new ArrayList<String>();
    static ArrayList<String> parola = new ArrayList<String>();
    static ArrayList<String> isEmri = new ArrayList<String>();
    static ArrayList<Integer> isEmriId = new ArrayList<Integer>();
    static ArrayList<Integer> birim = new ArrayList<Integer>();

    public static void main(String[] args) throws IOException {

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Port Baslatilamadi.....");
        }

        do {

            Socket client = serverSocket.accept();
            System.out.println("\tYeni Client Baglandi....");
            Scanner input = new Scanner(client.getInputStream());
            String secim = input.nextLine();
            if(secim.equals("1")) {

                ClientIslem islem = new ClientIslem(client);
                islem.start();
            }
            if(secim.equals("2")){

                Client1Islem islem1 = new Client1Islem(client);
                islem1.start();

            }

        } while (true);


    }
}
class ClientIslem extends Thread{

    private Socket client2;
    private Scanner cevapAl;
    private PrintWriter cevapYolla;

    public ClientIslem (Socket socket){
        client2 = socket;

        try {
            cevapAl = new Scanner(client2.getInputStream());
            cevapYolla = new PrintWriter(client2.getOutputStream(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void run() {


        String turMakina;
        String adMakina;
        String durumMakina;
        int hizMakina;

        int MakinaId = (MerkeziSunucu.id.size() + 1);

        adMakina = cevapAl.nextLine();
        turMakina = cevapAl.nextLine();
        durumMakina = cevapAl.nextLine();
        hizMakina = cevapAl.nextInt();
        cevapAl.nextLine();
        MerkeziSunucu.id.add(MakinaId);
        MerkeziSunucu.ad.add(adMakina);
        MerkeziSunucu.tur.add(turMakina);
        MerkeziSunucu.hiz.add(hizMakina);
        MerkeziSunucu.durum.add(durumMakina);
        cevapYolla.println(MakinaId);
        while(true){

            if(MerkeziSunucu.isEmri.size() == 0){
                String isDurum = "null";
                String isTur = "null";
                int isBirim = 0;
                int isId = 0;
                cevapYolla.println(isDurum);
                cevapYolla.println(isTur);
                cevapYolla.println(isBirim);
                cevapYolla.println(isId);
            }else {
                String makinaDurum = MerkeziSunucu.durum.get((MakinaId - 1));

                for (int i = 0; i < MerkeziSunucu.isEmri.size(); i++) {
                    int k = i;
                    cevapYolla.println(makinaDurum);
                    cevapYolla.println(MerkeziSunucu.isEmri.get(i));
                    cevapYolla.println(MerkeziSunucu.birim.get(i));
                    cevapYolla.println(MerkeziSunucu.isEmriId.get(i));
                    String gelenCevap = cevapAl.nextLine();
                    if (gelenCevap.equals("Girdi")) {

                        String gelenDurum = cevapAl.nextLine();
                        MerkeziSunucu.durum.set((MakinaId - 1), gelenDurum);
                        String gelenRapor = cevapAl.nextLine();
                        MerkeziSunucu.rapor.add(gelenRapor);
                        String degisenDurum = cevapAl.nextLine();
                        MerkeziSunucu.durum.set((MakinaId - 1), degisenDurum);
                        int ID = cevapAl.nextInt();
                        cevapAl.nextLine();
                        MerkeziSunucu.isEmri.remove(k);
                        MerkeziSunucu.birim.remove(k);
                        MerkeziSunucu.isEmriId.remove(k);

                    }

                }
            }
        }




    }


}
class Client1Islem extends Thread{

    private Socket client1;
    private Scanner girdiAl;
    private PrintWriter girdiYolla;


    public Client1Islem(Socket socket){
        client1 = socket;

        try {
            girdiAl = new Scanner(client1.getInputStream());
            girdiYolla = new PrintWriter(client1.getOutputStream(),true);
            MerkeziSunucu.kullanici.add("admin");
            MerkeziSunucu.parola.add("admin");
            MerkeziSunucu.kullanici.add("admin1");
            MerkeziSunucu.parola.add("admin1");
            MerkeziSunucu.kullanici.add("admin2");
            MerkeziSunucu.parola.add("admin2");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void run(){

        String adKullanici;
        String sifreKullanici;
        String kontrol = null;
        String kullaniciIslem;

        while(true){
            adKullanici = girdiAl.nextLine();
            sifreKullanici = girdiAl.nextLine();
            for(int i = 0; i < MerkeziSunucu.kullanici.size(); i++){
                String ad = MerkeziSunucu.kullanici.get(i);
                String sifre = MerkeziSunucu.parola.get(i);
                if(adKullanici.equals(ad) && sifreKullanici.equals(sifre)){
                    kontrol = "Basarili";
                    break;

                }else{
                    kontrol = "Basarisiz";
                }
            }
            String kontrol1 = kontrol;

            if(kontrol1.equals("Basarili")){
                girdiYolla.println(kontrol);
                break;
            }else{
                girdiYolla.println(kontrol);
            }
        }
        while(true){
            kullaniciIslem = girdiAl.nextLine();
            if(kullaniciIslem.equals("1")){

                int uzunluk = MerkeziSunucu.ad.size();
                girdiYolla.println(uzunluk);
                if(uzunluk == 0){
                    String yaziGonder = "Makina Listesi Bos.";
                    girdiYolla.println(yaziGonder);
                    String giris = girdiAl.nextLine();
                    if(giris.equals("hayir")){
                        break;
                    }
                }else {
                    for (int i = 0; i < uzunluk; i++) {
                        String ad = MerkeziSunucu.ad.get(i);
                        String tur = MerkeziSunucu.tur.get(i);
                        String durum = MerkeziSunucu.durum.get(i);
                        int hiz = MerkeziSunucu.hiz.get(i);
                        int id = MerkeziSunucu.id.get(i);
                        girdiYolla.println("ID = " + id + "\tMakina Adi = " + ad + "\tMakina Is Turu = " + tur + "\tMakinanin Birim Isi Yaptigi Sure = " + hiz + "\tMakina Durumu = " + durum);
                    }

                    String cevap = girdiAl.nextLine();
                    if (cevap.equals("hayir")) {
                        break;
                    }
                }
            }else if(kullaniciIslem.equals("2")){
                int uzunluk1 = MerkeziSunucu.ad.size();
                int raporUzunluk = MerkeziSunucu.rapor.size();
                girdiYolla.println(uzunluk1);
                girdiYolla.println(raporUzunluk);
                if(uzunluk1 == 0){
                    String yaziGonder = "Makina Listesi Bos.";
                    girdiYolla.println(yaziGonder);
                }else {
                    for (int i = 0; i < uzunluk1; i++) {
                        String ad1 = MerkeziSunucu.ad.get(i);
                        String tur1 = MerkeziSunucu.tur.get(i);
                        String durum1 = MerkeziSunucu.durum.get(i);
                        int hiz1 = MerkeziSunucu.hiz.get(i);
                        int id1 = MerkeziSunucu.id.get(i);
                        girdiYolla.println("ID = " + id1 + "\tMakina Adi = " + ad1 + "\tMakina Is Turu = " + tur1 + "\tMakinanin Birim Isi Yaptigi Sure = " + hiz1 + "\tMakina Durumu = " + durum1);
                    }
                }
                if(raporUzunluk == 0){
                    String bos = "Gosterilecek Rapor bulunamadi.";
                    girdiYolla.println(bos);
                    String giris1 = girdiAl.nextLine();
                    if(giris1.equals("hayir")){
                        break;
                    }
                }else{
                    for(int j = 0; j < raporUzunluk; j++){
                        String raporGoruntule = MerkeziSunucu.rapor.get(j);
                        girdiYolla.println(raporGoruntule);
                    }
                    String giris2 = girdiAl.nextLine();
                    if(giris2.equals("hayir")){
                        break;
                    }
                }
            }else if(kullaniciIslem.equals("3")){
                girdiYolla.println(MerkeziSunucu.isEmri.size());
                if(MerkeziSunucu.isEmri.size() == 0){
                    String gonder = "Is Emir Listesi Bos.";
                    girdiYolla.println(gonder);
                    String giris3 = girdiAl.nextLine();
                    if(giris3.equals("hayir")){
                        break;
                    }
                }else{
                    for(int i = 0; i < MerkeziSunucu.isEmri.size(); i++){
                        int isId = MerkeziSunucu.isEmriId.get(i);
                        int isBirim = MerkeziSunucu.birim.get(i);
                        String isTur = MerkeziSunucu.isEmri.get(i);
                        girdiYolla.println("Is ID = " + isId + "\tIsin Turu = " + isTur + "\tIstenilen Birim Is Miktari = " + isBirim + " birim");
                    }
                    String cevap1 = girdiAl.nextLine();
                    if(cevap1.equals("hayir")){
                        break;
                    }

                }
            }else if(kullaniciIslem.equals("4")){
                int idIs = girdiAl.nextInt();
                girdiAl.nextLine();
                String turIs = girdiAl.nextLine();
                int birimIs = girdiAl.nextInt();
                girdiAl.nextLine();
                MerkeziSunucu.isEmriId.add(idIs);
                MerkeziSunucu.isEmri.add(turIs);
                MerkeziSunucu.birim.add(birimIs);
                String cevap2 = girdiAl.nextLine();
                if(cevap2.equals("hayir")){
                    break;
                }
            }else if (kullaniciIslem.equals("5")){
                break;
            }

        }
        try
        {
            if (client1!=null)
            {
                System.out.println("Bir Client Baglantisi Kapatildi...");
                client1.close();
            }
        }
        catch(IOException ioEx)
        {
            System.out.println("Baglanti Koparilamadi......");
        }




    }

}
