package manager;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import modelo.Usuario;

import org.primefaces.event.SelectEvent;

import persistence.UsuarioDao;
import config.Util;

@ManagedBean
@ViewScoped
public class UsuarioMB implements Serializable {

	private static final long serialVersionUID = 1L;

	// Falta evitar que psicologas criem admins e outras psicologas
	// admins devem ter poder total

	private Usuario usuarioSelecionado;
	private List<Usuario> listaUsuario;

	private String senhaAtual;
	private String novaSenha1;
	private String novaSenha2;

	private UsuarioDao udao;

	@ManagedProperty(value = "#{loginMB.logado}")
	private Usuario logado;

	public UsuarioMB() {
		init();
	}

	@PostConstruct
	public void init() {
		usuarioSelecionado = logado;
	}

	public Usuario getLogado() {
		return logado;
	}

	public void setLogado(Usuario logado) {
		this.logado = logado;
	}

	public String getNovaSenha1() {
		return novaSenha1;
	}

	public void setNovaSenha1(String novaSenha1) {
		this.novaSenha1 = novaSenha1;
	}

	public String getNovaSenha2() {
		return novaSenha2;
	}

	public void setNovaSenha2(String novaSenha2) {
		this.novaSenha2 = novaSenha2;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public List<Usuario> listaUsuarios(String query) {
		return this.listaUsuario = new UsuarioDao().findByLoginOrNome(query);
	}

	public void limpaCampos() {
		Util.findComponent("gerenciaUsuario:senhaAtual").setRendered(false);
		Util.findComponent("gerenciaUsuario:lbSenhaAtual").setRendered(false);
		usuarioSelecionado = new Usuario();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Campos Limpos", "Campos Limpos"));
	}

	public void onItemSelect(SelectEvent event) {
		Util.findComponent("gerenciaUsuario:senhaAtual").setRendered(true);
		Util.findComponent("gerenciaUsuario:lbSenhaAtual").setRendered(true);
		usuarioSelecionado = (Usuario) event.getObject();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Usuário Selecionado", "Usuário Selecionado"));
	}

	public void onFocus() {
		Util.findComponent("gerenciaUsuario:senhaAtual").setRendered(true);
		Util.findComponent("gerenciaUsuario:lbSenhaAtual").setRendered(true);
		usuarioSelecionado = logado;
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Usuário Selecionado", "Usuário Selecionado"));
	}

	public void salvar() {
		udao = new UsuarioDao();
		try {
			// se for um usuario novo...
			if (usuarioSelecionado.getId() == null) {
				if (novaSenha1.equals(novaSenha2)
						&& (novaSenha1 != null | novaSenha1 != "")) {
					// verifica se o login foi preenchido
					if (!usuarioSelecionado.getLogin().equals("")
							| !usuarioSelecionado.getLogin().equals(null)) {
						// se foi, verifica se o novo login já existe
						if (udao.findLogin(usuarioSelecionado) != null) {
							throw new Exception();
						}
					}
					usuarioSelecionado.setSenha(getNovaSenha1());
					udao.create(usuarioSelecionado);
					FacesMessage msg = new FacesMessage("Usuário " + usuarioSelecionado.getLogin() + " Salvo", usuarioSelecionado.getNomeCompleto());
					FacesContext.getCurrentInstance().addMessage(null, msg);
				} else if (!novaSenha1.equals(novaSenha2)) {
					throw new ValidatorException(new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Senha Não Confere!!",
							"Digite a senha correta, por favor!"));
				}
			} else {
				// usuario do banco
				Usuario u = udao.findById(usuarioSelecionado.getId());
				// se nao for, verifica condições e altera
				// verifica se o login e senha informadas no form é o login e a
				// senha do banco
				if (/* (senhaAtual != null | senhaAtual != "") 
						&& */ senhaAtual.equals(u.getSenha())) {
					// verifica se o login foi alterado
					if (!usuarioSelecionado.getLogin().equals(u.getLogin())) {
						// se foi, verifica se o novo login já existe
						if (udao.findLogin(usuarioSelecionado) != null) {
							throw new Exception();
						}
					}
					// verifica se vai haver alteração de senha, se a
					// confirmação foi feita
					if (novaSenha1.equals(novaSenha2)
							&& (novaSenha1 != null | novaSenha1 != "")) {
						usuarioSelecionado.setSenha(novaSenha1);
						// verifica se a senha foi informada 2 vezes
					} else {
						if (!novaSenha1.equals(novaSenha2)) {
							throw new ValidatorException(new FacesMessage(
									FacesMessage.SEVERITY_ERROR,
									"Senha Não Confere!",
									"Digite a senha correta, por favor!"));
						}
					}
					udao.update(usuarioSelecionado);
					FacesMessage msg = new FacesMessage("Usuário " + usuarioSelecionado.getLogin() + " Salvo", usuarioSelecionado.getNomeCompleto());
					FacesContext.getCurrentInstance().addMessage(null, msg);
				} else {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Senha Não Confere",
									"Digite a senha correta, por favor!"));
				}
			}
		} catch (Exception e) {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Login já existe",
									"Já existe um usuário com este login, escolha outro!"));
		}
	}
}
