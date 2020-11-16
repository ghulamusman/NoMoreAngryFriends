<html>
<head>
<title>MainActivity.java</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
.s0 { color: #cc7832;}
.s1 { color: #a9b7c6;}
.s2 { color: #6897bb;}
.s3 { color: #6a8759;}
.s4 { color: #808080;}
</style>
</head>
<body bgcolor="#2b2b2b">
<table CELLSPACING=0 CELLPADDING=5 COLS=1 WIDTH="100%" BGCOLOR="#606060" >
<tr><td><center>
<font face="Arial, Helvetica" color="#000000">
MainActivity.java</font>
</center></td></tr></table>
<pre><span class="s0">package </span><span class="s1">com.example.nomoreangryfriends</span><span class="s0">;</span>

<span class="s0">import </span><span class="s1">androidx.appcompat.app.AppCompatActivity</span><span class="s0">;</span>
<span class="s0">import </span><span class="s1">androidx.core.app.ActivityCompat</span><span class="s0">;</span>
<span class="s0">import </span><span class="s1">android.Manifest</span><span class="s0">;</span>
<span class="s0">import </span><span class="s1">android.content.ComponentName</span><span class="s0">;</span>
<span class="s0">import </span><span class="s1">android.content.pm.PackageManager</span><span class="s0">;</span>
<span class="s0">import </span><span class="s1">android.os.Bundle</span><span class="s0">;</span>
<span class="s0">import </span><span class="s1">android.view.View</span><span class="s0">;</span>
<span class="s0">import </span><span class="s1">android.widget.EditText</span><span class="s0">;</span>
<span class="s0">import </span><span class="s1">android.widget.TextView</span><span class="s0">;</span>
<span class="s0">import </span><span class="s1">android.widget.Toast</span><span class="s0">;</span>
<span class="s0">import </span><span class="s1">java.io.FileNotFoundException</span><span class="s0">;</span>
<span class="s0">import </span><span class="s1">java.io.FileOutputStream</span><span class="s0">;</span>
<span class="s0">import </span><span class="s1">java.io.IOException</span><span class="s0">;</span>

<span class="s0">public class </span><span class="s1">MainActivity </span><span class="s0">extends </span><span class="s1">AppCompatActivity {</span>

    <span class="s0">private static final int </span><span class="s1">MY_PERMISSIONS_REQUEST_SEND_SMS = </span><span class="s2">1</span><span class="s0">;</span>
    <span class="s0">private static final int </span><span class="s1">MY_PERMISSIONS_REQUEST_RECEIVE_SMS = </span><span class="s2">1</span><span class="s0">;</span>
    <span class="s0">private static final </span><span class="s1">String FILE_NAME = </span><span class="s3">&quot;reply.txt&quot;</span><span class="s0">;</span>
    <span class="s0">private static final </span><span class="s1">String TAG = smsReceiver.</span><span class="s0">class</span><span class="s1">.getSimpleName()</span><span class="s0">;</span>

    <span class="s0">private </span><span class="s1">EditText replyText</span><span class="s0">;     </span><span class="s4">// for the new reply to be entered.</span>
    <span class="s0">private </span><span class="s1">TextView currentReply</span><span class="s0">;  </span><span class="s4">// the text view displays the current reply to be sent.</span>
    <span class="s0">private </span><span class="s1">getText currentText</span><span class="s0">;    </span><span class="s4">// loads text from file.</span>

    <span class="s1">@Override</span>
    <span class="s0">protected void </span><span class="s1">onCreate(Bundle savedInstanceState) {</span>
        <span class="s1">currentText = </span><span class="s0">new </span><span class="s1">getText()</span><span class="s0">;</span>
        <span class="s0">super</span><span class="s1">.onCreate(savedInstanceState)</span><span class="s0">;</span>
        <span class="s1">setContentView(R.layout.activity_main)</span><span class="s0">;</span>


        <span class="s1">checkForSmsPermission()</span><span class="s0">;</span>
        <span class="s0">if</span><span class="s1">(currentText.getMessageText(getApplicationContext()).length() == </span><span class="s2">0</span><span class="s1">){</span>
            <span class="s1">setSaveFile()</span><span class="s0">;</span>
        <span class="s1">}</span>
        <span class="s1">replyText = findViewById(R.id.replyText)</span><span class="s0">;</span>
        <span class="s1">currentReply = findViewById(R.id.currentText)</span><span class="s0">;</span>
        <span class="s1">currentReply.setText(currentText.getMessageText(getApplicationContext()))</span><span class="s0">;</span>



    <span class="s1">}</span>
    <span class="s0">private void </span><span class="s1">setSaveFile(){         </span><span class="s4">// Sets the save file with a default reply incase no reply has been specified.</span>
        <span class="s1">FileOutputStream fos = </span><span class="s0">null;</span>
        <span class="s0">try </span><span class="s1">{</span>
            <span class="s1">fos = openFileOutput(FILE_NAME</span><span class="s0">, </span><span class="s1">MODE_PRIVATE)</span><span class="s0">;</span>
            <span class="s1">fos.write(</span><span class="s3">&quot;Hey Dude!&quot;</span><span class="s1">.getBytes())</span><span class="s0">;</span>
        <span class="s1">} </span><span class="s0">catch </span><span class="s1">(FileNotFoundException e) {</span>
            <span class="s1">e.printStackTrace()</span><span class="s0">;</span>
        <span class="s1">} </span><span class="s0">catch </span><span class="s1">(IOException e) {</span>
            <span class="s1">e.printStackTrace()</span><span class="s0">;</span>
        <span class="s1">} </span><span class="s0">finally </span><span class="s1">{</span>
            <span class="s0">if </span><span class="s1">(fos != </span><span class="s0">null</span><span class="s1">) {</span>
                <span class="s0">try </span><span class="s1">{</span>
                    <span class="s1">fos.close()</span><span class="s0">;</span>
                <span class="s1">} </span><span class="s0">catch </span><span class="s1">(IOException e) {</span>
                    <span class="s1">e.printStackTrace()</span><span class="s0">;</span>
                <span class="s1">}</span>
            <span class="s1">}</span>
        <span class="s1">}</span>

    <span class="s1">}</span>

    <span class="s0">public void </span><span class="s1">onResume() {</span>
        <span class="s0">super</span><span class="s1">.onResume()</span><span class="s0">;</span>
        <span class="s1">ComponentName component = </span><span class="s0">new </span><span class="s1">ComponentName(</span><span class="s0">this, </span><span class="s1">smsReceiver.</span><span class="s0">class</span><span class="s1">)</span><span class="s0">;</span>
        <span class="s1">getPackageManager().setComponentEnabledSetting(component</span><span class="s0">, </span><span class="s1">PackageManager.COMPONENT_ENABLED_STATE_ENABLED</span><span class="s0">, </span><span class="s1">PackageManager.DONT_KILL_APP)</span><span class="s0">;</span>
    <span class="s1">}</span>

    <span class="s0">public void </span><span class="s1">onPause() {</span>
        <span class="s0">super</span><span class="s1">.onPause()</span><span class="s0">;</span>
        <span class="s1">ComponentName component = </span><span class="s0">new </span><span class="s1">ComponentName(</span><span class="s0">this, </span><span class="s1">smsReceiver.</span><span class="s0">class</span><span class="s1">)</span><span class="s0">;</span>
        <span class="s1">getPackageManager().setComponentEnabledSetting(component</span><span class="s0">, </span><span class="s1">PackageManager.COMPONENT_ENABLED_STATE_DISABLED</span><span class="s0">, </span><span class="s1">PackageManager.DONT_KILL_APP)</span><span class="s0">;</span>
    <span class="s1">}</span>


    <span class="s0">public void </span><span class="s1">saveReply(View view) {              </span><span class="s4">// saves the reply provided by the user locally to be used the next time a text is sent.</span>
        <span class="s1">String textToSave = replyText.getText().toString()</span><span class="s0">;</span>
        <span class="s1">FileOutputStream fos = </span><span class="s0">null;</span>
        <span class="s0">try </span><span class="s1">{</span>
            <span class="s1">fos = openFileOutput(FILE_NAME</span><span class="s0">, </span><span class="s1">MODE_PRIVATE)</span><span class="s0">;</span>
            <span class="s1">fos.write(textToSave.getBytes())</span><span class="s0">;</span>

            <span class="s1">replyText.getText().clear()</span><span class="s0">;</span>
            <span class="s1">Toast.makeText(</span><span class="s0">this, </span><span class="s3">&quot;Saved&quot;</span><span class="s0">,</span><span class="s1">Toast.LENGTH_LONG).show()</span><span class="s0">;</span>
        <span class="s1">} </span><span class="s0">catch </span><span class="s1">(FileNotFoundException e) {</span>
            <span class="s1">e.printStackTrace()</span><span class="s0">;</span>
        <span class="s1">} </span><span class="s0">catch </span><span class="s1">(IOException e) {</span>
            <span class="s1">e.printStackTrace()</span><span class="s0">;</span>
        <span class="s1">} </span><span class="s0">finally </span><span class="s1">{</span>
            <span class="s0">if </span><span class="s1">(fos != </span><span class="s0">null</span><span class="s1">) {</span>
                <span class="s0">try </span><span class="s1">{</span>
                    <span class="s1">fos.close()</span><span class="s0">;</span>
                <span class="s1">} </span><span class="s0">catch </span><span class="s1">(IOException e) {</span>
                    <span class="s1">e.printStackTrace()</span><span class="s0">;</span>
                <span class="s1">}</span>
            <span class="s1">}</span>
        <span class="s1">}</span>
        <span class="s1">currentReply.setText(currentText.getMessageText(getApplicationContext()))</span><span class="s0">;</span>
    <span class="s1">}</span>

    <span class="s4">// Checking for permissions to send and receive texts</span>

    <span class="s0">private void </span><span class="s1">checkForSmsPermission() {</span>
        <span class="s0">if </span><span class="s1">(ActivityCompat.checkSelfPermission(</span><span class="s0">this, </span><span class="s1">Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {</span>
            <span class="s1">ActivityCompat.requestPermissions(</span><span class="s0">this, new </span><span class="s1">String[]{Manifest.permission.SEND_SMS}</span><span class="s0">, </span><span class="s1">MY_PERMISSIONS_REQUEST_SEND_SMS)</span><span class="s0">;</span>
        <span class="s1">}</span>

        <span class="s0">if </span><span class="s1">(ActivityCompat.checkSelfPermission(</span><span class="s0">this, </span><span class="s1">Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {</span>
            <span class="s1">ActivityCompat.requestPermissions(</span><span class="s0">this, new </span><span class="s1">String[]{Manifest.permission.RECEIVE_SMS}</span><span class="s0">,  </span><span class="s1">MY_PERMISSIONS_REQUEST_RECEIVE_SMS)</span><span class="s0">;</span>
        <span class="s1">}</span>
    <span class="s1">}</span>

    <span class="s1">@Override</span>
    <span class="s0">public void </span><span class="s1">onRequestPermissionsResult(</span><span class="s0">int </span><span class="s1">requestCode</span><span class="s0">, </span><span class="s1">String permissions[]</span><span class="s0">, int</span><span class="s1">[] grantResults) {</span>

        <span class="s1">Toast.makeText(</span><span class="s0">this, </span><span class="s3">&quot;Permission Granted&quot;</span><span class="s0">,</span><span class="s1">Toast.LENGTH_LONG).show()</span><span class="s0">;</span>

    <span class="s1">}</span>



<span class="s1">}</span>
</pre>
</body>
</html>