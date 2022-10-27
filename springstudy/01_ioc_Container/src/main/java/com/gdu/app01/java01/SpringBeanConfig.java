package com.gdu.app01.java01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * @Configuration
 * 안녕. 나는 Bean을 만드는 Java file이야
 * Spring Bean Configuration File하고 하는 일이 같지
 */

@Configuration
public class SpringBeanConfig {
	
	// 메소드 하나당 Bean 하나를 맡아서 생성한다
	
	/*
	 * @Bean
	 * 안녕. 난 Bean을 만드는 메소드야
	 * 메소드 이름이 Bean의 이름(id)이고
	 * 반환 타입이 Bean의 타입(class)야
	 */
	
	/*
	 * <bean id="song1" class="Song">
	 * 		<property name="title" value="이차선다리">
	 * 		<property name="genre" value="트로트">
	 * </bean>
	 */
	@Bean
	public Song song1() {
		Song song = new Song();
		song.setTitle("이차선다리");
		song.setGenre("트로트");
		return song;
	}
	
	@Bean(name="song2")		// @Bean에 name 값을 지정하면 id로 사용된다
	public Song asdf() {	// 메소드 이름은 아무거나 적어도 된다
		Song song = new Song();
		song.setTitle("What2do");
		song.setGenre("R&B");
		return song;
	}
	
	/*
	 * <bean id="song1" class="Song">
	 * 		<constructor-arg value="이차선다리">
	 * 		<constructor-arg value="트로트">
	 * </bean>
	 */
	
	@Bean	
	public Song song3() {
		return new Song("I Fall in love too easily", "Jazz");
	}
	
	@Bean
	public Singer singer1() {
		Singer singer = new Singer();
		singer.setName("차태현");
		singer.setSong(song1());
		return singer;
	}
	
	@Bean(name="singer2")
	public Singer aer() {
		Singer singer = new Singer();
		singer.setName("Dean");
		singer.setSong(asdf());
		return singer;
	}
	
	@Bean
	public Singer singer3() {
		return new Singer("Chet Baker", song3());
	}
}

