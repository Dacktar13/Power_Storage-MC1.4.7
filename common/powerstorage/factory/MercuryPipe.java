package powerstorage.factory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.common.ForgeDirection;
import buildcraft.api.core.IIconProvider;
import buildcraft.transport.Pipe;
import buildcraft.transport.PipeTransportPower;
import buildcraft.transport.pipes.PipeLogic;

	public class MercuryPipe extends Pipe {

	        public MercuryPipe (int itemID) {
	                super(new PipeTransportPower(), new PipeLogic(), itemID);
	                ((PipeTransportPower) transport).powerResistance = 0.000;
	        }

            @Override
            @SideOnly(Side.CLIENT)
            public IIconProvider getIconProvider() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public int getIconIndex(ForgeDirection direction) {
                // TODO Auto-generated method stub
                return 0;
            }
	        
	   
	}