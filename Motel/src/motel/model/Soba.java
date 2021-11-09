package motel.model;

public class Soba {
	
	private String broj;
	private boolean tv;
	private boolean miniBar;
	private TipSobe tipSobe;
	private boolean obrisan;
	
	public Soba(String broj, boolean tv, boolean miniBar, TipSobe tipSobe) {
		super();
		this.broj = broj;
		this.tv = tv;
		this.miniBar = miniBar;
		this.tipSobe = tipSobe;
		this.obrisan = false;
	}
	
	public Soba(String broj, boolean tv, boolean miniBar, TipSobe tipSobe, boolean obrisan) {
		super();
		this.broj = broj;
		this.tv = tv;
		this.miniBar = miniBar;
		this.tipSobe = tipSobe;
		this.obrisan = obrisan;
	}
	
	public String ispis() {
		return obrisan + "|"+broj+"|"+tv+"|"+miniBar + "|"+tipSobe.getNaziv() + ":" + tipSobe.getBrojKreveta();
	}
	
	public String getBroj() {
		return broj;
	}
	public void setBroj(String broj) {
		this.broj = broj;
	}
	public boolean isTv() {
		return tv;
	}
	public void setTv(boolean tv) {
		this.tv = tv;
	}
	public boolean isMiniBar() {
		return miniBar;
	}
	public void setMiniBar(boolean miniBar) {
		this.miniBar = miniBar;
	}
	
	public TipSobe getTipSobe() {
		return tipSobe;
	}
	public void setTipSobe(TipSobe tipSobe) {
		this.tipSobe = tipSobe;
	}

	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}
	
	
	
	
	
	
	
	
}
