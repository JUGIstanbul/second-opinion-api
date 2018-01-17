package org.jugistanbul.secondopinion.api.config;

import org.jugistanbul.secondopinion.api.service.TruncateDatabaseService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseIT {

	@Autowired
	public TruncateDatabaseService truncateService;

	@Before
	public void truncateAllTables()
	{
		try
		{
			truncateService.truncate();
		}
		catch (Exception e)
		{
			throw new IllegalStateException(e);
		}
	}

}
