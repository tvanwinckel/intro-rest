package com.tvanwinckel.RestClient.infrastructure;

import com.tvanwinckel.RestClient.domain.ClientUser;
import com.tvanwinckel.RestClient.infrastructure.objects.User;
import com.tvanwinckel.RestClient.infrastructure.objects.UserName;
import org.springframework.web.reactive.function.client.WebClient;

public class UserWebClient {

    private final WebClient client;

    public UserWebClient(final String baseUrl) {
        client = WebClient.create(baseUrl);
    }

    public ClientUser getUserFromServer(final long id) {
        final User response = client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/users/{id}")
                        .build(id))
                .retrieve()
                .bodyToMono(User.class)
                .block();

        final UserName userName = response.userName();
        return new ClientUser(response.id().value(), userName.firstName(), userName.lastName());
    }

    public static void main(String[] args) {
        final UserWebClient webClient = new UserWebClient("http://localhost:8080/");
        final ClientUser response = webClient.getUserFromServer(900354607755954473L);

        System.out.println("Result: " + response);
    }
}
