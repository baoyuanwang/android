package com.example.rabbit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import io.adaptivecards.objectmodel.ActionType;
import io.adaptivecards.objectmodel.AdaptiveCard;
import io.adaptivecards.objectmodel.BaseActionElement;
import io.adaptivecards.objectmodel.BaseCardElement;
import io.adaptivecards.objectmodel.HostConfig;
import io.adaptivecards.objectmodel.OpenUrlAction;
import io.adaptivecards.objectmodel.ParseContext;
import io.adaptivecards.objectmodel.ParseResult;
import io.adaptivecards.objectmodel.ShowCardAction;
import io.adaptivecards.objectmodel.SubmitAction;
import io.adaptivecards.renderer.AdaptiveCardRenderer;
import io.adaptivecards.renderer.RenderedAdaptiveCard;
import io.adaptivecards.renderer.actionhandler.ICardActionHandler;


public class AdaptiveCardActivity extends AppCompatActivity {

    private String TAG = "AdaptiveCardActivity";
    private LinearLayout linearLayout;
    private CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adaptive_card);
        //调用微软的Adaptive card的SDK 开始渲染卡片
        linearLayout = findViewById(R.id.toAdaptiveCard_linearLayout);
        cardView = findViewById(R.id.toAdaptiveCard_cardView);
        try {
            ParseContext context = new ParseContext(); // Empty parseContext so only known elements up to v1.2 will be parsed
            String jsonText = getJsonString();
            Log.d(TAG, "Json: " + jsonText);
            ParseResult parseResult = AdaptiveCard.DeserializeFromString(jsonText, AdaptiveCardRenderer.VERSION, context);
            AdaptiveCard adaptiveCard = parseResult.GetAdaptiveCard();
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            //创建hostconfig 示例
            HostConfig hostConfig = new HostConfig();
            RenderedAdaptiveCard renderedCard = AdaptiveCardRenderer.getInstance().render(AdaptiveCardActivity.this, supportFragmentManager, adaptiveCard, new ICardActionHandler() {
                @Override
                public void onAction(BaseActionElement actionElement, RenderedAdaptiveCard renderedAdaptiveCard) {
                    ActionType actionType = actionElement.GetElementType();
                    if (actionType == ActionType.Submit) {
                        onSubmit(actionElement, renderedAdaptiveCard);
                    } else if (actionType == ActionType.ShowCard) {
                        onShowCard(actionElement);
                    } else if (actionType == ActionType.OpenUrl) {
                        onOpenUrl(actionElement);
                    } else if (actionType == ActionType.Custom) {
                        //Handle activation of custom actions
                        // ...
                    }
                }

                @Override
                public void onMediaPlay(BaseCardElement baseCardElement, RenderedAdaptiveCard renderedAdaptiveCard) {

                }

                @Override
                public void onMediaStop(BaseCardElement baseCardElement, RenderedAdaptiveCard renderedAdaptiveCard) {

                }
            }, hostConfig);
            View view = renderedCard.getView();
            //直接将view添加到部件管理器上
            //linearLayout.addView(view);
            //直接将view添加到activity上的效果
//            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(1000,800);
//            addContentView(view, layoutParams);
            //将iew添加到cardview上的效果
            cardView.addView(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getJsonString() throws IOException {
        Log.d(TAG, "getFilesDir: " + getFilesDir().getAbsolutePath());
        Log.d(TAG, "getCacheDir: " + getCacheDir().getAbsolutePath());
        Log.d(TAG, "getDataDir: " + getDataDir().getAbsolutePath());
        // 读取assets目录里的test.json文件，获取字节输入流
        InputStream is = getResources().openRawResource(R.raw.adptivecard);
        // 获取字节输入流长度
        int length = is.available();
        // 定义字节缓冲区
        byte[] buffer = new byte[length];
        // 读取字节输入流，存放到字节缓冲区里
        is.read(buffer);
        // 将字节缓冲区里的数据转换成utf-8字符串
        String json = new String(buffer, "utf-8");
        return json;
    }

    private void onSubmit(BaseActionElement actionElement, RenderedAdaptiveCard renderedAdaptiveCard) {
        SubmitAction submitAction = null;
        if (actionElement instanceof SubmitAction) {
            submitAction = (SubmitAction) actionElement;
        } else if ((submitAction = SubmitAction.dynamic_cast(actionElement)) == null) {
            throw new InternalError("Unable to convert BaseActionElement to ShowCardAction object model.");
        }
        String data = submitAction.GetDataJson();
        Map<String, String> keyValueMap = renderedAdaptiveCard.getInputs();
        if (!data.isEmpty() && !data.startsWith("null")) {
            try {
                JSONObject object = new JSONObject(data);
                showToast("Submit data: " + object.toString() + "\nInput: " + keyValueMap.toString(), Toast.LENGTH_LONG);
            } catch (JSONException e) {
                showToast(e.toString(), Toast.LENGTH_LONG);
            }
        } else {
            showToast("Submit input: " + keyValueMap.toString(), Toast.LENGTH_LONG);
        }
    }

    private void showToast(String s, int lengthLong) {
        Toast.makeText(cardView.getContext(), s, lengthLong).show();
    }

    private void onShowCard(BaseActionElement actionElement) {
        ShowCardAction showCardAction = null;
        if (actionElement instanceof ShowCardAction) {
            showCardAction = (ShowCardAction) actionElement;
        } else if ((showCardAction = ShowCardAction.dynamic_cast(actionElement)) == null) {
            throw new InternalError("Unable to convert BaseActionElement to ShowCardAction object model.");
        }

        //Get the card from show card and create a view
        //RenderedAdaptiveCard renderedCard = AdaptiveCardRenderer.getInstance().render(context, fragmentManager, showCardAction.GetCard(), cardActionHandler, hostConfig);
        //ViewGroup viewGroup = (ViewGroup) renderedCard.getView();

    }

    private void onOpenUrl(BaseActionElement actionElement) {
        OpenUrlAction openUrlAction = null;
        if (actionElement instanceof ShowCardAction) {
            openUrlAction = (OpenUrlAction) actionElement;
        } else if ((openUrlAction = OpenUrlAction.dynamic_cast(actionElement)) == null) {
            throw new InternalError("Unable to convert BaseActionElement to ShowCardAction object model.");
        }
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(openUrlAction.GetUrl()));
        this.startActivity(browserIntent);
    }

}