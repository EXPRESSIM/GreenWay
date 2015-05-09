﻿{
    items: [
		{
		    text: "信息管理",
		    items: [
                { text: "快递管理", view: "ShippingServicer" },
                { text: "文章管理", view: "Article" },
				{ text: "商品类别管理", view: "Category" },
				{ text: "商品管理", view: "Product" },
				{ text: "产品费用管理", view: "ProductFee" }
			],
		    isRoot: true
		},
		{
		    text: "合作机构管理",
		    items: [
				{ text: "供应商管理", view: "Supplier" },
				{ text: "检测机构管理", view: "Library" },
				{ text: "CDC管理", view: "CDC" }
			],
		    isRoot: true
		},
		{
		    text: "交易管理",
		    items: [
				{ text: "费用管理", view: "Fee" },
				{ text: "订单管理", view: "Order" }
			],
		    isRoot: true
		},
		{
		    text: "检测管理",
		    items: [
				{ text: "检测类别管理", view: "Examination" },
				{ text: "检测报告查询", view: "FormBrowser" },
				{ text: "检测试剂管理", items: [
					{ text: "试剂管理", view: "Agentia" },
					{ text: "批号管理", view: "BatchNumber" }
				]
				}
			],
		    isRoot: true
		},
		{
		    text: "专家咨询",
		    items: [
				{ text: "问题解答", view: "Questions" }
			],
		    isRoot: true
		},
		{
		    text: "统计",
		    items: [
				{ text: "列表", items: [
					{ text: "用户列表", view: "ReportUser" }
				]
				},
				{ text: "财务", items: [
					{ text: "产品销售", view: "ReportSalesSummary" }
				]
				},
                { text: "检测", items: [
			                { text: "检测结果统计", view: "ReportResultSummary" }
		                ]
                  }
	        ],
		    isRoot: true
		},
		{
		    text: "系统",
		    items: [
				{ text: "修改密码", view: "ChangePassword" },
				{ text: "退出", view: "Exit" },
				{ text: "关于", view: "About" }
			],
		    isRoot: true
		}
	]
}