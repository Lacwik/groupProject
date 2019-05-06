package com.wfiis.CalculatorCO2.resourceFlags.metadata;

import com.wfiis.CalculatorCO2.resourceFlags.metadata.entity.ResourceFlags;
import com.wfiis.CalculatorCO2.resourceFlags.model.ResourceFlagsCreateModel;
import org.springframework.stereotype.Component;

@Component
public class ResourceFlagsAssembler {

    public ResourceFlagsCreateModel getCreateModelFromEntity(ResourceFlags resourceFlags) {
        return new ResourceFlagsCreateModel(
                resourceFlags.getWaterFlag(),
                resourceFlags.getDieselFlag(),
                resourceFlags.getLpgFlag(),
                resourceFlags.getFoilFlag()
        );
    }

    public ResourceFlags getNewEntityFromModel(ResourceFlagsCreateModel resourceFlagsCreateModel){
        return new ResourceFlags(
                null,
                resourceFlagsCreateModel.getWaterFlag(),
                resourceFlagsCreateModel.getDieselFlag(),
                resourceFlagsCreateModel.getLpgFlag(),
                resourceFlagsCreateModel.getFoilFlag()
        );
    }
}
