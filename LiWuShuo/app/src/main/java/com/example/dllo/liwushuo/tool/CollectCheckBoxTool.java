package com.example.dllo.liwushuo.tool;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.dllo.liwushuo.register.BmobCollectBean;
import com.example.dllo.liwushuo.register.BmobRaidersBean;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by dllo on 16/6/12.
 */
public class CollectCheckBoxTool {
    public BmobQuery<BmobCollectBean> query = new BmobQuery<>();
    public BmobQuery<BmobRaidersBean> query1 = new BmobQuery<>();
    private Context context;
    private boolean flag = true;
    private BmobUser user;

    public CollectCheckBoxTool(Context context) {
        this.context = context;
        user = BmobUser.getCurrentUser(context);
    }

    public void queryIsLike(String id, final CheckBox checkBox) {

        if (user != null) {
            query.addWhereEqualTo("id", id);
            query.addWhereEqualTo("userName", user.getUsername());
            query.findObjects(context, new FindListener<BmobCollectBean>() {
                @Override
                public void onSuccess(List<BmobCollectBean> list) {
                    if (list.size() == 0) {
                        checkBox.setChecked(false);
                    } else {
                        checkBox.setChecked(true);
                    }
                }

                @Override
                public void onError(int i, String s) {
                    Toast.makeText(context, "查询失败" + s, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    public void cancleLike(String id) {
        Toast.makeText(context, "取消喜欢成功", Toast.LENGTH_SHORT).show();

        BmobQuery<BmobCollectBean> query = new BmobQuery<>();
        //条件查询
        query.addWhereEqualTo("id", id);
        query.addWhereEqualTo("userName", user.getUsername());

        //查询到对应id的数据库内容
        query.findObjects(context, new FindListener<BmobCollectBean>() {
            @Override
            public void onSuccess(List<BmobCollectBean> list) {

                //遍历这个List
                for (BmobCollectBean bmobCollectBean : list) {
                    //进行删除
                    bmobCollectBean.delete(context, new DeleteListener() {
                        @Override
                        public void onSuccess() {
                            //删除成功
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            Toast.makeText(context, "删除失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }

    public void cancleLikeRaiders(String id) {
        Toast.makeText(context, "取消喜欢成功", Toast.LENGTH_SHORT).show();

        BmobQuery<BmobRaidersBean> query = new BmobQuery<>();
        //条件查询
        query.addWhereEqualTo("id", id);
        query.addWhereEqualTo("userName", user.getUsername());

        //查询到对应id的数据库内容
        query.findObjects(context, new FindListener<BmobRaidersBean>() {
            @Override
            public void onSuccess(List<BmobRaidersBean> list) {

                //遍历这个List
                for (BmobRaidersBean bmobCollectRaidersBean : list) {
                    //进行删除
                    bmobCollectRaidersBean.delete(context, new DeleteListener() {
                        @Override
                        public void onSuccess() {
                            //删除成功
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            Toast.makeText(context, "删除失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }

    List<String> ids = new ArrayList<>();
    public void queryAllLike(final BaseAdapter baseAdapter){

        if (user != null) {
            query1.addWhereEqualTo("userName", user.getUsername());
            query1.findObjects(context, new FindListener<BmobRaidersBean>() {
                @Override
                public void onSuccess(List<BmobRaidersBean> list) {
                    for (BmobRaidersBean bmobRaidersBean : list) {
                        ids.add(bmobRaidersBean.getId());
                        baseAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onError(int i, String s) {
                    Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            ids = new ArrayList<>();
            baseAdapter.notifyDataSetChanged();
        }
    }

    public void queryAllLike(){
        BmobUser user = BmobUser.getCurrentUser(context);
        if (user != null) {
            query1.addWhereEqualTo("userName", user.getUsername());
            query1.findObjects(context, new FindListener<BmobRaidersBean>() {
                @Override
                public void onSuccess(List<BmobRaidersBean> list) {
                    for (BmobRaidersBean bmobRaidersBean : list) {
                        ids.add(bmobRaidersBean.getId());

                    }
                }

                @Override
                public void onError(int i, String s) {
                    Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    public void queryIsLikeRaiders(String id, final CheckBox checkBox) {
        BmobUser user = BmobUser.getCurrentUser(context);
        if (user != null) {
            checkBox.setChecked(ids.contains(id));
        }
    }
}
