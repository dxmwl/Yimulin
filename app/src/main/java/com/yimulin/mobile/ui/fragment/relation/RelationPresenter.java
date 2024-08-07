package com.yimulin.mobile.ui.fragment.relation;

import android.content.Context;
import android.util.Log;

import com.yimulin.mobile.http.model.Relation;
import com.yimulin.mobile.ui.base.BasePresenter;
import com.yimulin.mobile.utils.FileUtils;
import com.yimulin.mobile.utils.GsonUtils;

import java.util.List;

/**
 * <pre>
 *      @author hurley
 *      date : 2018/10/23
 *      github : https://github.com/HurleyJames
 *      desc :
 * </pre>
 */
public class RelationPresenter extends BasePresenter implements RelationContract.Presenter {
    private static final String TAG = "RelationPresenter";

    private RelationContract.View mView;

    public static RelationPresenter newInstance() {
        return new RelationPresenter();
    }

    /**
     * 从本地读取数据
     *
     * @param relationList
     * @param context
     * @return
     */
    public List getRelationByJSON(final List<Relation.ResultBean.RelationBean> relationList, Context context) {
        String jsonContext = FileUtils.readFileFromAssets("relation.json", context);
        final Relation relation = GsonUtils.getObject(jsonContext, Relation.class);
        for (int i = 0; i < relation.getResult().getRelation().size(); i++) {
            relationList.add(new Relation.ResultBean.RelationBean(relation.getResult().getRelation().get(i).getName(),
                    relation.getResult().getRelation().get(i).getFather(),
                    relation.getResult().getRelation().get(i).getMother(),
                    relation.getResult().getRelation().get(i).getBro1(),
                    relation.getResult().getRelation().get(i).getBro2(),
                    relation.getResult().getRelation().get(i).getSis1(),
                    relation.getResult().getRelation().get(i).getSis2(),
                    relation.getResult().getRelation().get(i).getHusband(),
                    relation.getResult().getRelation().get(i).getWife(),
                    relation.getResult().getRelation().get(i).getSon(),
                    relation.getResult().getRelation().get(i).getDaughter()));
        }
        return relationList;
    }


    /**
     * 获得关系称呼
     *
     * @param buffer
     * @param relationList
     * @return
     */
    public String getRelationship(StringBuffer buffer, List<Relation.ResultBean.RelationBean> relationList) {
        //去除所有"的"字符
        String call = buffer.toString().replace("的", "");
        Log.e(TAG, call.substring(1, 3));
        //中间称呼
        String temp = "";
        //下一个关系
        String nextRelation;
        for (int i = 0; i < relationList.size(); i++) {
            Log.d(TAG, "list长度：" + relationList.size());
            Log.d(TAG, "buffer长度：" + buffer.length());
            Log.d(TAG, "去掉'的'后的长度：" + call.length());
            //第一个关系是"我的XX"，肯定称呼为"XX"，所以直接判断哪些等于"XX"
            if (relationList.get(i).getName().equals(call.substring(1, 3))) {
                Log.d(TAG, "i的位置：" + i);
                Log.d(TAG, "第一个关系：" + call.substring(1, 3));
                temp = call.substring(1, 3);
                int gap = 2;
                for (int j = 3; j < call.length(); j = j + gap) {
                    nextRelation = call.substring(j, j + 2);
                    Log.e(TAG, "下一个关系：" + nextRelation);

                    if (nextRelation.equals("爸爸")) {
                        temp = relationList.get(i).getFather();
                        Log.e(TAG, "temp改变：" + temp);
                    }
                    if (nextRelation.equals("妈妈")) {
                        temp = relationList.get(i).getMother();
                        Log.e(TAG, "temp改变：" + temp);
                    }
                    if (nextRelation.equals("哥哥")) {
                        temp = relationList.get(i).getBro1();
                        Log.e(TAG, "temp改变：" + temp);
                    }
                    if (nextRelation.equals("弟弟")) {
                        temp = relationList.get(i).getBro2();
                        Log.e(TAG, "temp改变：" + temp);
                    }
                    if (nextRelation.equals("姐姐")) {
                        temp = relationList.get(i).getSis1();
                        Log.e(TAG, "temp改变：" + temp);
                    }
                    if (nextRelation.equals("妹妹")) {
                        temp = relationList.get(i).getSis2();
                        Log.e(TAG, "temp改变：" + temp);
                    }
                    if (nextRelation.equals("丈夫")) {
                        temp = relationList.get(i).getHusband();
                        Log.e(TAG, "temp改变：" + temp);
                    }
                    if (nextRelation.equals("妻子")) {
                        temp = relationList.get(i).getWife();
                        Log.e(TAG, "temp改变：" + temp);
                    }
                    if (nextRelation.equals("儿子")) {
                        temp = relationList.get(i).getSon();
                        Log.e(TAG, "temp改变：" + temp);
                    }
                    if (nextRelation.equals("女儿")) {
                        temp = relationList.get(i).getDaughter();
                        Log.e(TAG, "temp改变：" + temp);
                    }

                    Log.d(TAG, "temp：" + temp);
                    //获取temp的位置
                    for (int k = 0; k < relationList.size(); k++) {
                        if (relationList.get(k).getName().equals(temp)) {
                            i = k;
                            Log.e(TAG, "temp的位置：" + i);
                        }
                    }
                }
                break;
            }
        }
        call = temp;
        Log.e(TAG, "最终称呼：" + call);
        return call;
    }
}
