package powerstorage.factory;

import powerstorage.CommonProxy;
import net.minecraftforge.common.ForgeDirection;
import buildcraft.transport.Pipe;
import buildcraft.transport.PipeTransportPower;
import buildcraft.transport.pipes.PipeLogicGold;

	public class MercuryPipe extends Pipe {

	        public MercuryPipe (int itemID) {
	                super(new PipeTransportPower(), new PipeLogicGold(), itemID);
	                ((PipeTransportPower) transport).powerResistance = 0.000;
	        }
	        
	        @Override
	    	public String getTextureFile() {
	    		return CommonProxy.TEXTURE_BLOCKS;
	    	}

	    	@Override
	    	public int getTextureIndex(ForgeDirection direction) {
	    		return 0;
	    	}
	}