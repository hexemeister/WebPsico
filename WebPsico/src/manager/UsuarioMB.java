package manager;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.el.PropertyNotFoundException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import modelo.Usuario;

import org.primefaces.event.SelectEvent;

import persistence.UsuarioDao;
import config.Util;

@ManagedBean
@ViewScoped
public class UsuarioMB {

	Usuario usuarioSelecionado;
	List<Usuario> listaUsuario;

	String senhaAtual;
	String novaSenha1;
	String novaSenha2;

	UsuarioDao udao;

	public UsuarioMB() {
		init();
	}

	@PostConstruct
	public void init() {
		usuarioSelecionado = ((LoginMB) Util.getObjectSession("loginMB"))
				.getLogado();
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
		usuarioSelecionado = new Usuario();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Campos Limpos", "Campos Limpos"));
	}

	public void onItemSelect(SelectEvent event) {
		usuarioSelecionado = (Usuario) event.getObject();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Usuário Selecionado", "Usuário Selecionado"));
	}

	public void salvar() {
		udao = new UsuarioDao();
		try {
			// se for um usuario novo...
			if (usuarioSelecionado.getId() == null) {
				usuarioSelecionado.setSenha(getSenhaAtual());
				udao.create(usuarioSelecionado);
			} else {
				// usuario do banco
				Usuario u = udao.FindById(usuarioSelecionado.getId());
				// se nao for, verifica condições e altera
				// verifica se o login e senha informadas no form é o login e a
				// senha do banco
				if ((senhaAtual != null | senhaAtual != "")
						&& senhaAtual.equals(u.getSenha())) {
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
						usuarioSelecionado.setSenha(senhaAtual);
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
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage("Usuario "
									+ usuarioSelecionado.getLogin() + " Salvo",
									"Usuário Salvo"));
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
		// limpaCampos();
	}
}
