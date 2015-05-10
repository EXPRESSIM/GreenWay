package com.stardust.express.bo;

import com.stardust.express.dao.abstracts.DataGateFactory;
import com.stardust.express.models.DataModel;
import com.stardust.express.models.User;
import com.stardust.express.tools.Cryptor;
import com.stardust.express.tools.IViewContext;

public class UserAdminBO extends AdminBO {

	public UserAdminBO(IViewContext context) {
		super(context);
		gate = DataGateFactory.getUserGate("");
	}

	@Override
	protected DataModel _createModel(IViewContext ctx) {
		return new User(ctx);
	}
	
	@Override
	 protected Boolean _preUpdate(DataModel model)
    {
		User user = (User)model;
		if (user.getPassword().length() <= 16) {
			user.setPassword(Cryptor.GetMD5Code(user.getPassword()));
		}
        return true;
    }

}
