{
	items: [
		{
			text: "信息管理", 
			items:[
				{text:"商品管理", view:"Product"},
				{text:"用户管理", items:[
					{text:"供应商管理员管理", view:"SupplierUser"}
				]}
			],
			isRoot: true
		},
		{
			text:"交易管理",
			items:[
				{text:"订单管理", view:"Order"}
			],
			isRoot: true
		},
		{
			text: "系统",
			items: [
				{ text: "修改密码", view: "ChangePassword" },
				{text:"退出",view:"Exit"},
				{ text: "关于", view: "About" }
			],
			isRoot: true
		}
	] 
}