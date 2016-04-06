package br.com.servidor.utilites;

import br.com.servidor.model.QuestGeolocalizada;
import br.com.servidor.model.Usuario;

public class UsuarioReferenciaCircular {
	private QuestReferenciaCircular questsReferenciaCircular;
	
	public UsuarioReferenciaCircular(){
		questsReferenciaCircular = new QuestReferenciaCircular();
	}
	
	public Usuario removendoReferenciasCirculares(Usuario usuario) {
		for(QuestGeolocalizada quest : usuario.getMinhasQuests()) {
			quest.setUsuario(null);
			quest = questsReferenciaCircular.removendoReferenciasCirculares(quest);
		}
		return usuario;
	}
	
	public Usuario adicionandoReferenciasCirculares(Usuario usuario) {
		if(usuario.getMinhasQuests() != null) {
            for(QuestGeolocalizada quest : usuario.getMinhasQuests()) {
                    quest.setUsuario(null);
                    quest = questsReferenciaCircular.removendoReferenciasCirculares(quest);
            }
        }
		return usuario;
	}

}
