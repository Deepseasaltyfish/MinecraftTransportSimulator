package minecrafttransportsimulator.packets.instances;

import io.netty.buffer.ByteBuf;
import minecrafttransportsimulator.entities.components.AEntityA_Base;
import minecrafttransportsimulator.entities.instances.EntityVehicleF_Physics;
import minecrafttransportsimulator.mcinterface.AWrapperWorld;
import minecrafttransportsimulator.mcinterface.InterfaceManager;
import minecrafttransportsimulator.packets.components.APacketEntity;

public class PacketVehicleWaypointSelectRequest extends APacketEntity<EntityVehicleF_Physics> {
    private final String waypointIndex;
    private final String waypointListIndex;
    private final String loopMode;

    public PacketVehicleWaypointSelectRequest(EntityVehicleF_Physics entity, String waypointIndex,String waypointListIndex,String loopMode) {
        super(entity);
        this.waypointIndex = waypointIndex;
        this.waypointListIndex = waypointListIndex;
        this.loopMode = loopMode;
    }

    public PacketVehicleWaypointSelectRequest(ByteBuf buf) {
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
    protected boolean handle(AWrapperWorld world, EntityVehicleF_Physics vehicle) {
        if (!world.isClient()) {
            vehicle.selectedWaypointIndex = waypointIndex;
            vehicle.selectedWaypointListIndex = waypointListIndex;
            vehicle.loopMode = loopMode;
            InterfaceManager.packetInterface.sendToAllClients(new PacketVehicleWaypointSelect(vehicle, waypointIndex,waypointListIndex, loopMode));
        }
        return true;
    }
}
