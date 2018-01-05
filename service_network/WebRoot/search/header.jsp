<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<header><!-- Define the header section of the page -->

        <div class="logo"><!-- Define the logo element -->
            <a href="#">
                <img src="images/logo.png" title="" alt="" />
            </a>
        </div>
        <section id="search"><!-- Search form -->
            <form action="keywordsearch.html"  method="get" name="searchform" id="searchform">
            Key Word：<input onFocus="if (this.value =='please input key word' ) this.value='' " onBlur="if (this.value=='') this.value='please input key word'" type="text" name="keyWord" id="ser" value="${keyWord }"/>
                <input type="submit" value="">
            </form>
        </section>
    </header>