package com.ireal.crims.sgws.enums;

public enum SgAppNodeStatu {
/*	SG_APPNODE_ADDED = 0, ///< 已经增加。增加应用节点后，新建应用节点对象，并且应用节点对象立即转入"正在连接"状态。
	SG_APPNODE_CONNECTING, ///< 正在连接。增加应用节点时，进入"已经增加"之后直接进入这个状态。连接失败或连接中断后、超时到期后，也进入这个状态。
	SG_APPNODE_FAILED, ///< 连接失败。处于"正在连接"状态，并且连接出错时转入此状态。进入此状态后，等待重连超时，超时到期后，转入"正在连接"状态。
	SG_APPNODE_WORKING, ///< 正在工作。处于"正在连接"状态，并且连接成功时转入此状态。进入此状态后，如果网络中断，转入到"连接中断"状态。
	SG_APPNODE_BROKEN, ///< 连接中断。处于"正在工作状"态，并且网络中断时转入此状态。进入此状态后，等待重连超时，超时到期后，转入"正在连接"状态。
	SG_APPNODE_REMOVED, ///< 已经删除。处于其它状态时，执行删除应用节点操作，无条件进入此状态。进入此状态后，中断网络连接，同时从系统中删除应用节点对象。
*/	
	
	SG_APPNODE_ADDED(0, "已经增加"),
	SG_APPNODE_CONNECTING(1, "正在连接"),
	SG_APPNODE_FAILED(2, "连接失败"),
	SG_APPNODE_WORKING(4, "正在工作"),
	SG_APPNODE_BROKEN(5, "连接中断"),
	SG_APPNODE_REMOVED(6, "已经删除");

    // 普通方法
    public static SgAppNodeStatu getAppNodeStatu(int status) {
        for (SgAppNodeStatu type : SgAppNodeStatu.values())
        {
	        if ( type.getStatus() == status )
	        {
	            return type;
	        }
        }
        return null;
    }
    
     // 覆盖方法
    @Override
    public String toString() {
        return this.status + "_" + this.statusName;
    }
	
	private int status;
	private String statusName;

	private SgAppNodeStatu(int status, String statusName)
	{
		this.status = status;
		this.statusName = statusName;
	}

	public int getStatus() {
		return status;
	}

	public String getStatusName() {
		return statusName;
	}

}
