package minecrafttransportsimulator.packets.instances;

import io.netty.buffer.ByteBuf;
import minecrafttransportsimulator.entities.instances.EntityVehicleF_Physics;
import minecrafttransportsimulator.mcinterface.AWrapperWorld;
import minecrafttransportsimulator.packets.components.APacketEntity;

public class PacketVehicleWaypointSelect extends APacketEntity<EntityVehicleF_Physics> {
    private final String waypointIndex;
    private final String waypointListIndex;
    private final String loopMode;

    public PacketVehicleWaypointSelect(EntityVehicleF_Physics entity, String waypointIndex,String waypointListIndex,String loopMode) {
        super(entity);
        this.waypointIndex = waypointIndex;
        this.waypointListIndex = waypointListIndex;
        //loop: whether loop waypointList or not, a bool value
        this.loopMode = loopMode;
    }

    public PacketVehicleWaypointSelect(ByteBuf buf) {
        super(buf);
        this.waypointIndex = readStringFromBuffer(buf);
        this.waypointListIndex = readStringFromBuffer(buf);
        this.loopMode = readStringFromBuffer(buf);
    }

    @Override
    public void writeToBuffer(ByteBuf buf) {
        super.writeToBuffer(buf);
        writeStringToBuffer(waypointIndex, buf);
        writeStringToBuffer(waypointListIndex,buf);
        writeStringToBuffer(loopMode,buf);
    }

    @Override
    public boolean handle(AWrapperWorld world, EntityVehicleF_Physics vehicle) {
        vehicle.selectedWaypointIndex = waypointIndex;
        vehicle.selectedWaypointListIndex = waypointListIndex;
        vehicle.loopMode = loopMode;
        return true;
    }
}
