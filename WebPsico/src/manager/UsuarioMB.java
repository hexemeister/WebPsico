package manager;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

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

	public UsuarioMB() {
		usuarioSelecionado = ((LoginMB) Util.getObjectSession("loginMB"))
				.getLogado();
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
		this.usuarioSelecionado = new Usuario();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Campos Limpos", "Campos Limpos"));
	}

	public void onItemSelect(SelectEvent event) {
		usuarioSelecionado = (Usuario) event.getObject();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Usuário Selecionado", "Usuário Selecionado"));
	}

	public void salvar() {
		try {
			UsuarioDao udao = new UsuarioDao();
			if (senhaAtual != null
					&& senhaAtual.equals((udao.FindById(usuarioSelecionado
							.getId()).getSenha()))) {
				if (usuarioSelecionado.getId().equals(
						udao.FindById(usuarioSelecionado.getId()))
						&& usuarioSelecionado.getLogin() != udao
								.findByLoginAndSenha(usuarioSelecionado)
								.getLogin()) {
					if (udao.findLogin(usuarioSelecionado) == null) {
						throw new Exception();
					}
				}
				if (novaSenha1.equals(novaSenha2) && novaSenha1 != null
						&& novaSenha1 != "") {
					usuarioSelecionado.setSenha(novaSenha1);
				} else if(!novaSenha1.equals(novaSenha2) ) {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Senha Não Confere!",
									"Digite a senha correta, por favor!"));
				}
				udao.update(usuarioSelecionado);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage("Usuario "
								+ usuarioSelecionado.getLogin() + " Salvo",
								"Usuário Salvo"));
				limpaCampos();
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Senha Não Confere",
								"Digite a senha correta, por favor!"));
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
