<html>
<head>
<title>getText.java</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
.s0 { color: #cc7832;}
.s1 { color: #a9b7c6;}
.s2 { color: #6a8759;}
</style>
</head>
<body bgcolor="#2b2b2b">
<table CELLSPACING=0 CELLPADDING=5 COLS=1 WIDTH="100%" BGCOLOR="#606060" >
<tr><td><center>
<font face="Arial, Helvetica" color="#000000">
getText.java</font>
</center></td></tr></table>
<pre><span class="s0">package </span><span class="s1">com.example.nomoreangryfriends</span><span class="s0">;</span>

<span class="s0">import </span><span class="s1">android.content.Context</span><span class="s0">;</span>

<span class="s0">import </span><span class="s1">java.io.BufferedReader</span><span class="s0">;</span>
<span class="s0">import </span><span class="s1">java.io.FileInputStream</span><span class="s0">;</span>
<span class="s0">import </span><span class="s1">java.io.FileNotFoundException</span><span class="s0">;</span>
<span class="s0">import </span><span class="s1">java.io.IOException</span><span class="s0">;</span>
<span class="s0">import </span><span class="s1">java.io.InputStreamReader</span><span class="s0">;</span>

<span class="s0">public class </span><span class="s1">getText {</span>
    <span class="s0">public </span><span class="s1">String getMessageText(Context context){</span>
        <span class="s1">String textToSend = </span><span class="s2">&quot;Hello&quot;</span><span class="s0">;</span>
        <span class="s1">FileInputStream fis = </span><span class="s0">null;</span>

        <span class="s0">try </span><span class="s1">{</span>
            <span class="s1">fis = context.openFileInput(</span><span class="s2">&quot;reply.txt&quot;</span><span class="s1">)</span><span class="s0">;</span>
            <span class="s1">InputStreamReader isr = </span><span class="s0">new </span><span class="s1">InputStreamReader(fis)</span><span class="s0">;</span>
            <span class="s1">BufferedReader br = </span><span class="s0">new </span><span class="s1">BufferedReader(isr)</span><span class="s0">;</span>
            <span class="s1">StringBuilder sb = </span><span class="s0">new </span><span class="s1">StringBuilder()</span><span class="s0">;</span>
            <span class="s1">String text</span><span class="s0">;</span>

            <span class="s0">while </span><span class="s1">((text = br.readLine()) != </span><span class="s0">null</span><span class="s1">) {</span>
                <span class="s1">sb.append(text).append(</span><span class="s2">&quot;</span><span class="s0">\n</span><span class="s2">&quot;</span><span class="s1">)</span><span class="s0">;</span>
            <span class="s1">}</span>

            <span class="s1">textToSend = sb.toString()</span><span class="s0">;</span>

        <span class="s1">} </span><span class="s0">catch </span><span class="s1">(FileNotFoundException e) {</span>
            <span class="s1">e.printStackTrace()</span><span class="s0">;</span>
        <span class="s1">} </span><span class="s0">catch </span><span class="s1">(IOException e) {</span>
            <span class="s1">e.printStackTrace()</span><span class="s0">;</span>
        <span class="s1">} </span><span class="s0">finally </span><span class="s1">{</span>
            <span class="s0">if </span><span class="s1">(fis != </span><span class="s0">null</span><span class="s1">) {</span>
                <span class="s0">try </span><span class="s1">{</span>
                    <span class="s1">fis.close()</span><span class="s0">;</span>
                <span class="s1">} </span><span class="s0">catch </span><span class="s1">(IOException e) {</span>
                    <span class="s1">e.printStackTrace()</span><span class="s0">;</span>
                <span class="s1">}</span>
            <span class="s1">}</span>
        <span class="s1">}</span>

        <span class="s0">return </span><span class="s1">textToSend</span><span class="s0">;</span>
    <span class="s1">}</span>
<span class="s1">}</span>
</pre>
</body>
</html>