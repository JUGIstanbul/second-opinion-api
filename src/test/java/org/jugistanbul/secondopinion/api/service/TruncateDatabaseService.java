package org.jugistanbul.secondopinion.api.service;

import org.hibernate.Session;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TruncateDatabaseService {

	private EntityManager entityManager;

	@Autowired
	public TruncateDatabaseService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Transactional
	public void truncate() throws Exception {
		List<String> tableNames = new ArrayList<>();
		Session session = entityManager.unwrap(Session.class);
		Map<String, ClassMetadata> hibernateMetadata = session.getSessionFactory().getAllClassMetadata();

		for (ClassMetadata classMetadata : hibernateMetadata.values()) {
			AbstractEntityPersister aep = (AbstractEntityPersister) classMetadata;
			tableNames.add(aep.getTableName());
		}

		entityManager.flush();
		entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();
		tableNames.forEach(tableName -> entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate());
		entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
	}
}
