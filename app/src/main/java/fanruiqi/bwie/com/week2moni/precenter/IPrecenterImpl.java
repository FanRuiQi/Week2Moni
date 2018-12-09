package fanruiqi.bwie.com.week2moni.precenter;

import fanruiqi.bwie.com.week2moni.callback.MyCallBack;
import fanruiqi.bwie.com.week2moni.model.IModelImpl;
import fanruiqi.bwie.com.week2moni.view.IView;

public class IPrecenterImpl implements IPrencenter{
    IView mIView;
    IModelImpl iModel;

    public IPrecenterImpl(IView IView) {
        mIView = IView;
        iModel = new IModelImpl();
    }

    @Override
    public void startRequest(String url, String params) {

        iModel.requestData(url, params, new MyCallBack() {
            @Override
            public void backData(Object data) {
                mIView.shouData(data);
            }
        });
    }

    public void Detoch(){
        if (mIView!=null){
            mIView=null;
        }
        if (iModel!=null){
            iModel=null;
        }
    }
}
