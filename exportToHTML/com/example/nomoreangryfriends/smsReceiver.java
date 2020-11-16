<html>
<head>
<title>smsReceiver.java</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
.s0 { color: #cc7832;}
.s1 { color: #a9b7c6;}
.s2 { color: #6a8759;}
.s3 { color: #808080;}
.s4 { color: #6897bb;}
</style>
</head>
<body bgcolor="#2b2b2b">
<table CELLSPACING=0 CELLPADDING=5 COLS=1 WIDTH="100%" BGCOLOR="#606060" >
<tr><td><center>
<font face="Arial, Helvetica" color="#000000">
smsReceiver.java</font>
</center></td></tr></table>
<pre><span class="s0">package </span><span class="s1">com.example.nomoreangryfriends</span><span class="s0">;</span>

<span class="s0">import </span><span class="s1">android.annotation.TargetApi</span><span class="s0">;</span>
<span class="s0">import </span><span class="s1">android.content.BroadcastReceiver</span><span class="s0">;</span>
<span class="s0">import </span><span class="s1">android.content.Context</span><span class="s0">;</span>
<span class="s0">import </span><span class="s1">android.content.Intent</span><span class="s0">;</span>
<span class="s0">import </span><span class="s1">android.os.Build</span><span class="s0">;</span>
<span class="s0">import </span><span class="s1">android.os.Bundle</span><span class="s0">;</span>
<span class="s0">import </span><span class="s1">android.telephony.SmsManager</span><span class="s0">;</span>
<span class="s0">import </span><span class="s1">android.telephony.SmsMessage</span><span class="s0">;</span>
<span class="s0">import </span><span class="s1">android.util.Log</span><span class="s0">;</span>
<span class="s0">import </span><span class="s1">android.widget.Toast</span><span class="s0">;</span>


<span class="s0">public class </span><span class="s1">smsReceiver </span><span class="s0">extends </span><span class="s1">BroadcastReceiver {</span>

    <span class="s0">private static final </span><span class="s1">String TAG = smsReceiver.</span><span class="s0">class</span><span class="s1">.getSimpleName()</span><span class="s0">;</span>
    <span class="s0">public static final </span><span class="s1">String pdu_type = </span><span class="s2">&quot;pdus&quot;</span><span class="s0">;</span>

    <span class="s1">@TargetApi(Build.VERSION_CODES.M)</span>
    <span class="s1">@Override</span>
    <span class="s0">public void </span><span class="s1">onReceive(Context context</span><span class="s0">, </span><span class="s1">Intent intent) { </span><span class="s3">// Receiving and processing data from the text message received using a broadcast receiver.</span>

        <span class="s1">getText currentText = </span><span class="s0">new </span><span class="s1">getText()</span><span class="s0">;</span>
        <span class="s1">Bundle bundle = intent.getExtras()</span><span class="s0">;</span>
        <span class="s1">SmsMessage[] msgs</span><span class="s0">;</span>
        <span class="s1">String phoneNumber = </span><span class="s2">&quot;&quot;</span><span class="s0">;</span>
        <span class="s1">String receivedMessage = </span><span class="s2">&quot;&quot;</span><span class="s0">;</span>
        <span class="s1">String format = bundle.getString(</span><span class="s2">&quot;format&quot;</span><span class="s1">)</span><span class="s0">;</span>
        <span class="s1">Object[] pdus = (Object[]) bundle.get(pdu_type)</span><span class="s0">;</span>

        <span class="s1">msgs = </span><span class="s0">new </span><span class="s1">SmsMessage[pdus.length]</span><span class="s0">;</span>

        <span class="s0">for </span><span class="s1">(</span><span class="s0">int </span><span class="s1">i = </span><span class="s4">0</span><span class="s0">; </span><span class="s1">i &lt; msgs.length</span><span class="s0">; </span><span class="s1">i++) {</span>
            <span class="s1">msgs[i] = SmsMessage.createFromPdu((</span><span class="s0">byte</span><span class="s1">[]) pdus[i]</span><span class="s0">, </span><span class="s1">format)</span><span class="s0">;</span>
            <span class="s1">phoneNumber += msgs[i].getOriginatingAddress()</span><span class="s0">;</span>
            <span class="s1">receivedMessage += msgs[i].getMessageBody()</span><span class="s0">;</span>
        <span class="s1">}</span>

        <span class="s1">Log.d(TAG</span><span class="s0">, </span><span class="s2">&quot;Received text from: &quot; </span><span class="s1">+ phoneNumber)</span><span class="s0">;</span>
        <span class="s1">Toast.makeText(context</span><span class="s0">, </span><span class="s1">phoneNumber+</span><span class="s2">&quot; :&quot;</span><span class="s1">+receivedMessage</span><span class="s0">, </span><span class="s1">Toast.LENGTH_LONG).show()</span><span class="s0">;</span>

        <span class="s1">sendText(currentText.getMessageText(context)</span><span class="s0">,</span><span class="s1">phoneNumber)</span><span class="s0">;  </span><span class="s3">//  Get Reply from the getText class.</span>
    <span class="s1">}</span>


    <span class="s0">private void </span><span class="s1">sendText(String messageContent</span><span class="s0">, </span><span class="s1">String phoneNumber){     </span><span class="s3">// Method to send a specified text message to a specified number.</span>

        <span class="s1">System.out.println(messageContent + </span><span class="s2">&quot; &quot; </span><span class="s1">+ phoneNumber)</span><span class="s0">;</span>
        <span class="s1">Log.d(TAG</span><span class="s0">, </span><span class="s2">&quot;Sent text To: &quot; </span><span class="s1">+ phoneNumber)</span><span class="s0">;</span>

        <span class="s1">SmsManager smsManager = SmsManager.getDefault()</span><span class="s0">;</span>
        <span class="s1">smsManager.sendTextMessage(phoneNumber</span><span class="s0">, null, </span><span class="s1">messageContent</span><span class="s0">, null, null</span><span class="s1">)</span><span class="s0">;</span>

    <span class="s1">}</span>
<span class="s1">}</span>
</pre>
</body>
</html>