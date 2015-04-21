package manager;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	
	

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public UsuarioMB() {
		usuarioSelecionado = ((LoginMB) Util.getObjectSession("loginMB")).getLogado();
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
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Campos Limpos", "Campos Limpos"));
	}
	
	public void onItemSelect(SelectEvent event) {
			usuarioSelecionado = (Usuario) event.getObject();
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Usuário Selecionado", "Usuário Selecionado"));
	}

	public void salvar() {
		System.out.println("-------------------"+senhaAtual);
		if (senhaAtual!=null && senhaAtual.equals((new UsuarioDao().FindById(usuarioSelecionado.getId()).getSenha()))) {
			new UsuarioDao().update(usuarioSelecionado);
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Usuario " + usuarioSelecionado.getLogin() + " Salvo", "Usuário Salvo"));
			this.usuarioSelecionado = new Usuario();
		}else {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha Não Confere", "Digite a senha correta, por favor!"));
		}
		
	}
	
}
