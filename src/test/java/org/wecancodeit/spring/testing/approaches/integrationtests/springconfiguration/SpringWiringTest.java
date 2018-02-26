package org.wecancodeit.spring.testing.approaches.integrationtests.springconfiguration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * This test does nothing more than load the Spring context. This is a good way
 * to verify that we've not missed some basic wiring.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringWiringTest {

	@Test
	public void contextLoads() {
	}

}
