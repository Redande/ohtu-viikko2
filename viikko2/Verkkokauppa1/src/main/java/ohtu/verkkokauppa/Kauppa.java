package ohtu.verkkokauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Kauppa {

    private VarastoRajapinta varasto;
    private PankkiRajapinta pankki;
    private Ostoskori ostoskori;
    private GeneraattoriRajapinta viitegeneraattori;
    private String kaupanTili;

    @Autowired
    public Kauppa(VarastoRajapinta varasto, PankkiRajapinta pankki, GeneraattoriRajapinta generaattori) {
        this.varasto = varasto;
        this.pankki = pankki;
        this.viitegeneraattori = generaattori;
        kaupanTili = "33333-44455";
    }

    public void aloitaAsiointi() {
        ostoskori = new Ostoskori();
    }

    public void poistaKorista(int id) {
        Tuote t = varasto.haeTuote(id);
        varasto.palautaVarastoon(t);
    }

    public void lisaaKoriin(int id) {
        if (varasto.saldo(id)>0) {
            Tuote t = varasto.haeTuote(id);
            ostoskori.lisaa(t);
            varasto.otaVarastosta(t);
        }
    }

    public boolean tilimaksu(String nimi, String tiliNumero) {
        int viite = viitegeneraattori.uusi();
        int summa = ostoskori.hinta();

        return pankki.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa);
    }

    public KirjanpitoRajapinta kirjanpito() {
        return pankki.kirjanpito();
    }

    public void checkstyletRikkovaMetodi() {
        if (true)
        {
        if (true)
        {
        if (true)
        {

        }
        }
        }

        for (int i = 0; i < 5; i++)
        {
        for (int j = 0; j < 2; j++)
        {
        for (int k = 0; k < 3; k++)
        {

        }
        }
        }
    }

}
