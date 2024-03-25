package com.example.ImperiaConquest.Resource;

import com.example.ImperiaConquest.Constants.ResourceTypes;
import com.example.ImperiaConquest.Empire.Empire;
import org.springframework.beans.factory.annotation.Autowired;

public class ResourceService {
     private ResourcesRepository resourcesRepository;

     public ResourceService(ResourcesRepository resourcesRepository) {
        this.resourcesRepository = resourcesRepository;
     }

    public void createResource(Empire empire, ResourceTypes type, int amount) {
        Resource resource = new Resource();
        resource.setEmpire(empire);
        resource.setType(type.toString());
        resource.setAmount(amount);
        resourcesRepository.save(resource);
    }

    public ResourcesRepository getResourcesRepository() {
        return resourcesRepository;
    }

    public void setResourcesRepository(ResourcesRepository resourcesRepository) {
        this.resourcesRepository = resourcesRepository;
    }
}
