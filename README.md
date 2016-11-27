#FloatingActionButton


#先看项目演示
![image](https://github.com/fanjianli/YanShi/blob/master/fabs.gif)

该控件理论上最低支持到API版本14也就是Android4.0**（minSdkVersion 14）**，并且由于是官方Support Library中FloatingActionButton的二次封装，showdown的生成在API21以上和21以下并不太一样，所以在不同版本的系统中的效果会存在一定的差异。

######该控件依赖了以下两个support library，使用者无需在项目里再次添加 （Don't need to add）。

    com.android.support:design:23.+
    com.android.support:cardview-v7:23.+



如图提供了四种position方式，默认为**right_bottom**。其他为**right_top**、**left_bottom**、**left_top**。在CoordinatorLayout中建议不要定位到top，会被toolbar挡住。
**position**可在XML布局中设置，也可在JAVA代码中设置。
####XML
在com.fjl.floatingactionbutton.FloatingActionButtonPlus中添加

        app:position="left_top"

value还可以是**right_bottom**、**left_bottom**、**left_top**
####Java Code
首先有四个常量分别为

      public static final int POS_LEFT_TOP = 0;
      public static final int POS_LEFT_BOTTOM = 1;
      public static final int POS_RIGHT_TOP = 2;
      public static final int POS_RIGHT_BOTTOM = 3;

通过FloatingActionButtonPlus对象设置

      mActionButtonPlus = (FloatingActionButtonPlus) findViewById(R.id.FabPlus);
      mActionButtonPlus.setPosition(FloatingActionButtonPlus.POS_LEFT_TOP);

###Animation
Animation暂时给了三种，分别为**fade、scale、bounce**，默认为scale。后续会可能会提供接口供使用者扩展。Animation同样可以在XML中或Java中设置。
####XML
在com.fjl.floatingactionbutton.FloatingActionButtonPlus中添加  **fade、scale、bounce**三个值中的一个。如：

     app:animationMode = "scale"

另可设置动画持续时间，单位为毫秒，默认为150毫秒

     app:animationDuration = "300"


####Java code
首先有四个常量分别为

    public static final int ANIM_FADE = 0;
    public static final int ANIM_SCALE = 1;
    public static final int ANIM_BOUNCE = 2;

通过FloatingActionButtonPlus对象设置

    mActionButtonPlus.setPosition(FloatingActionButtonPlus.POS_LEFT_TOP);
    mActionButtonPlus.setAnimation(FloatingActionButtonPlus.ANIM_SCALE);

动画持续时间可通过

    mActionButtonPlus.setAnimationDuration(300);

###Events
暂时只给出了item的点击事件，如果有更多类型事件的需求，欢迎Star。

    mActionButtonPlus.setOnItemClickListener(new FloatingActionButtonPlus.OnItemClickListener() {
            @Override
            public void onItemClick(FabTagLayout tagView, int position) {
                Toast.makeText(MainActivity.this, "Click btn" + position, Toast.LENGTH_SHORT).show();
            }
        });

###Scroll show or hide
关于滑动显示隐藏，这里有两种方法，
#####1、使用CoordinatorLayout

使用CoordinatorLayout的话，首先要确保你的外层layout是`android.support.design.widget.CoordinatorLayout`，如我上面activity_main.xml中的实例代码。之后你需要在`com.fjl.floatingactionbutton.FloatingActionButtonPlus`中添加上`app:layout_behavior="com.fjl.floatingactionbutton.FabBehavior"`，如下

    <com.fjl.floatingactionbutton.FloatingActionButtonPlus
    android:id="@+id/FabPlus"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:switchFabColor="#DB4537"
    app:switchFabIcon="@mipmap/ic_add_white_48dp"
    app:layout_behavior="com.lzp.floatingactionbuttonplus.FabBehavior"
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    </com.lzp.floatingactionbuttonplus.FloatingActionButtonPlus>


#####2、监听滑动距离（judge scorll distance）
在没有使用CoordinatorLayout的情况下，我给出了两个public method。 分别为showFab() 和  hideFab()。通过FloatingActionButtonPlus对象去调用。所以你如果想要实现通过这个效果，需要你去获取当前Scroll的距离。例如在RecyclerView中你可以这么写：

     mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_DRAGGING) {
                    if (dy > 0) {
                        mActionButtonPlus.hideFab();
                    } else {
                        mActionButtonPlus.showFab();
                    }
                }
            }
        });

###More settings
***
####XML
######com.fjl.floatingactionbutton.FloatingActionButtonPlus

     app:switchFabIcon="@mipmap/ic_add_white_48dp"   <!--设置主Fab的icon图标-->
     app:switchFabColor="#DB4537"                    <!--设置主Fab的颜色-->
     app:mBackgroundColor="#99ffffff"                <!--设置item展开后的背景颜色，默认为alpha99的白色-->

#####com.fjl.floatingactionbutton.FabTagLayout

     app:tagText="text"                          <!--设置item中lable中显示的文字-->

***

####Java Code
######com.fjl.floatingactionbutton.FloatingActionButtonPlus
	mActionButtonPlus.setContentIcon(getResources().getDrawable(R.mipmap.ic_add_white_48dp)); //设置主Fab的icon图标
    mActionButtonPlus.setRotateValues(45); //设置主Fab被点击时旋转的度数，默认为45度
    boolean state = mActionButtonPlus.getSwitchFabDisplayState();  //获取当前Fab的显示状态，显示时返回true，隐藏返回false

#####com.fjl.floatingactionbutton.FabTagLayout

     tagView.setTagText("text");  //设置label中显示的文字



