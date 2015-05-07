MSShop.DataGridConfig = {};

MSShop.DataGridConfig.ArticleMaintLookup = [
     { label: "ID",
         dbField: "ID",
         property: "Id",
         gridOptions: "width:100,field:'Id'",
         isSearchField: false
     },
        { label: "标题",
            dbField: "TITLE",
            property: "Title",
            gridOptions: "width:200,field:'Title'",
            isSearchField: true,
            isKeyField: true
        },
        { label: "作者",
            dbField: "AUTHOR",
            property: "Author",
            gridOptions: "width:200,field:'Author'",
            isSearchField: true,
            isKeyField: false
        }
]

MSShop.DataGridConfig.LibraryMaintLookup = [
    { label: "ID",
        dbField: "ID",
        property: "Id",
        gridOptions: "width:100,field:'Id'",
        isSearchField: false
    },
    { label: "名称",
        dbField: "LIBRARY_NAME",
        property: "Name",
        gridOptions: "width:200,field:'Name'",
        isSearchField: true,
        isKeyField: true
    },
    { label: "地址",
        dbField: "ADDRESS",
        property: "Address",
        gridOptions: "width:200,field:'Address'",
        isSearchField: true,
        isKeyField: false
    }
];

 MSShop.DataGridConfig.ShippingServicerMaintLookup = [
    { label: "ID",
        dbField: "ID",
        property: "Id",
        gridOptions: "width:100,field:'Id'",
        isSearchField: false
    },
    { label: "名称",
        dbField: "SERVICER_NAME",
        property: "ServicerName",
        gridOptions: "width:200,field:'ServicerName'",
        isSearchField: true,
        isKeyField: true
    },
    { label: "代码",
        dbField: "SERVICER_CODE",
        property: "ServicerCode",
        gridOptions: "width:200,field:'ServicerCode'",
        isSearchField: true,
        isKeyField: false
    }
];

MSShop.DataGridConfig.CDCMaintLookup = [
    { label: "ID",
        dbField: "ID",
        property: "Id",
        gridOptions: "width:100,field:'Id'",
        isSearchField: false
    },
    { label: "名称",
        dbField: "CDC_NAME",
        property: "Name",
        gridOptions: "width:200,field:'Name'",
        isSearchField: true,
        isKeyField: true
    },
    { label: "地址",
        dbField: "CDC_ADDRESS",
        property: "Address",
        gridOptions: "width:200,field:'Address'",
        isSearchField: true,
        isKeyField: false
    }
];

  MSShop.DataGridConfig.CategoryMaintLookup = [
            { label: "ID",
                dbField: "ID",
                property: "Id",
                gridOptions: "width:100,field:'Id'",
                isSearchField: false
            },
            { label: "类别",
                dbField: "CATEGORY_NAME",
                property: "CategoryName",
                gridOptions: "width:200,field:'CategoryName'",
                isSearchField: true,
                isKeyField: true
            }
        ];

MSShop.DataGridConfig.ShippingServicerMaintLookup = [
    { label: "ID",
        dbField: "ID",
        property: "Id",
        gridOptions: "width:100,field:'Id'",
        isSearchField: false
    },
    { label: "名称",
        dbField: "SERVICER_NAME",
        property: "ServicerName",
        gridOptions: "width:200,field:'ServicerName'",
        isSearchField: true,
        isKeyField: true
    },
        { label: "名称",
            dbField: "SERVICER_CODE",
            property: "ServicerCode",
            gridOptions: "width:200,field:'ServicerCode'",
            isSearchField: true,
            isKeyField: false
        }
];

 MSShop.DataGridConfig.SupplierLookup = [
            { label: "ID",
                dbField: "ID",
                property: "Id",
                gridOptions: "width:30,field:'Id'",
                isSearchField: false
            },
            { label: "名称",
                dbField: "SUPPLIER_NAME",
                property: "SupplierName",
                gridOptions: "width:100,field:'SupplierName'",
                isSearchField: true,
                isKeyField: true
            },
            { label: "地址",
                dbField: "ADDRESS",
                property: "Address",
                gridOptions: "width:200,field:'Address'",
                isSearchField: true,
                isKeyField: false
            },
            { label: "状态",
                dbField: "STATUS",
                property: "Status",
                gridOptions: "width:30,field:'Status'",
                isSearchField: true,
                isKeyField: false
            }
        ];

 MSShop.DataGridConfig.ProductLookup = [
            { label: "ID",
                dbField: "ID",
                property: "Id",
                gridOptions: "width:30,field:'Id'",
                isSearchField: false
            },
            { label: "编号",
                dbField: "PRODUCT_NUMBER",
                property: "ProductNo",
                gridOptions: "width:100,field:'ProductNo'",
                isSearchField: true,
                isKeyField: true
            },
            { label: "名称",
                dbField: "PRODUCT_NAME",
                property: "Name",
                gridOptions: "width:200,field:'Name'",
                isSearchField: true,
                isKeyField: false
            },
            { label: "状态",
                dbField: "STATUS",
                property: "Status",
                gridOptions: "width:30,field:'Status'",
                isSearchField: true,
                isKeyField: false
            },
            { label: "价格",
                dbField: "PRICE",
                property: "Price",
                gridOptions: "width:30,field:'Price'",
                isSearchField: false,
                isKeyField: false
            },
            { label: "数量",
                dbField: "QUANTITY",
                property: "Quantity",
                gridOptions: "width:30,field:'Quantity'",
                isSearchField: false,
                isKeyField: false
            }
        ];