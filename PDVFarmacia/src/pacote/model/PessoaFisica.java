package pacote.model;

public abstract class PessoaFisica extends Pessoa {

	private String rg;
	private String cpf;
	
	public PessoaFisica() {
		
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
