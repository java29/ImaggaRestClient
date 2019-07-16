package telran.imagga.controller;

import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import telran.imagga.dto.ResponseDto;
import telran.imagga.dto.TagDto;

public class ImaggaTagsAppl {

	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic <your secure>");
		String url = "https://api.imagga.com/v2/tags";
		UriComponentsBuilder builder = 
				UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("image_url", "https://24smi.org/public/media/235x307/person/2017/12/22/4sqqykgn04bo-cheburashka.jpg")
				.queryParam("language", "en,ru")
				.queryParam("limit", 3);
		RequestEntity<String> requestEntity = 
				new RequestEntity<>(headers, HttpMethod.GET, 
						builder.build().encode().toUri());
		ResponseEntity<ResponseDto> responseEntity = 
				restTemplate.exchange(requestEntity, ResponseDto.class);
		printTags(responseEntity.getBody().getResult().getTags());

	}

	private static void printTags(TagDto[] tags) {
		Arrays.stream(tags).forEach(System.out::println);
				
		
	}

}
