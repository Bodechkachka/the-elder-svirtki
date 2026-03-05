package com.alfa.the_elder_svirtki

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(TestcontainersConfiguration::class)
@SpringBootTest
class TheElderSvirtkiApplicationTests {

	@Test
	fun contextLoads() {
	}

}
