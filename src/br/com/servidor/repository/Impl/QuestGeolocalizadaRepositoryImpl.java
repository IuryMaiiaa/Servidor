package br.com.servidor.repository.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enumeration.QueryType;
import br.com.servidor.model.CoordenadaGeografica;
import br.com.servidor.model.QuestGeolocalizada;
import br.com.servidor.model.Usuario;
import br.com.servidor.repository.QuestGeolocalizadaRepository;

public class QuestGeolocalizadaRepositoryImpl extends JpaGenericRepositoryImpl<QuestGeolocalizada> implements QuestGeolocalizadaRepository{

	@Override
	public List<QuestGeolocalizada> findQuestsByCordenadas(
			List<CoordenadaGeografica> cordenadas) {
		ArrayList<QuestGeolocalizada> quests = new ArrayList<QuestGeolocalizada>();
		
		for (CoordenadaGeografica cordenada : cordenadas) {
			if(cordenada!=null) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("idCordenada", cordenada.getID());
				
				List<QuestGeolocalizada> result  = find(QueryType.JPQL,
						"from QuestGeolocalizada where id_cordenada=:idCordenada", params);
				
				if(result != null && !result.isEmpty()) {
					quests.addAll(result);
				}
			}
		}
		if(quests.isEmpty()) {
			
			return null;
		} else {
			return quests;
		}
		
	}

}
