package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import br.com.voxdatati.auditlib.ActiveMQService;
import br.com.voxdatati.auditlib.providers.ActionProvider;
import br.com.voxdatati.auditlib.providers.NetworkProvider;
import br.com.voxdatati.auditlib.providers.ProjectProvider;
import br.com.voxdatati.auditlib.providers.UserProvider;

@Component
public class AuditConfig {

	@Value("${audit.host}")
	private String auditBrokerHost;
	
	@Value("${audit.queue}")
	private String auditQueue;
	
    @Bean
    public ActiveMQService activeMQService() {
        ActiveMQService amq = new ActiveMQService();
        amq.setBrokerURL(auditBrokerHost);
        amq.setQueue(auditQueue);
        return amq;
    }

    // Configura o Provider de informações do usuário
    @Bean
    public UserProvider userProvider() {
        return new UserProvider() {

            @Override
            public String accessKey() {
                // ...
                // Lógica para obter este atribudo
                return "accessKey";
            }

            @Override
            public String login() {
                // ...
                // Lógica para obter este atribudo
                return "login";
            }

            @Override
            public String userExternalKey() {
                // ...
                // Lógica para obter este atribudo
                return "userExternalKey";
            }

            @Override
            public String username() {
                // ...
                // Lógica para obter este atribudo
                return "username";
            }

        };
    }

    // Configura o Provider de informações de rede
    @Bean
    public NetworkProvider networkProvider() {
        return new NetworkProvider(){

            @Override
            public String geoLocalization() {
                // ...
                // Lógica para obter este atribudo
                return "geoLocalization";
            }

            @Override
            public String ip() {
                // ...
                // Lógica para obter este atribudo
                return "ip";
            }

            @Override
            public String logicDoor() {
                // ...
                // Lógica para obter este atribudo
                return "logicDoor";
            }

            @Override
            public String macAddress() {
                // ...
                // Lógica para obter este atribudo
                return "macAddress";
            }

            @Override
            public String user() {
                // ...
                // Lógica para obter este atribudo
                return "user";
            }

            @Override
            public String workstation() {
                // ...
                // Lógica para obter este atribudo
                return "workstation";
            }

        };
    }

    // Configura o Provider de informações da ação
    @Bean
    public ActionProvider actionProvider(){
        return new ActionProvider(){

            @Override
            public String key() {
                // ...
                // Lógica para obter este atribudo
                return "key";
            }

            @Override
            public String name() {
                // ...
                // Lógica para obter este atribudo
                return "name";
            }

            @Override
            public String tree() {
                // ...
                // Lógica para obter este atribudo
                return "tree";
            }

            @Override
            public String type() {
                // ...
                // Lógica para obter este atribudo
                return "type";
            }

        };
    }
    
    @Bean
    public ProjectProvider projectProvider() {
    	return new ProjectProvider() {
			
			@Override
			public String chave() {
				return "teste";
			}
		};
    }


}
