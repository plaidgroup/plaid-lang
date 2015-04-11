# Syntax Highlighting #

It is most convenient and helpful to have syntax highlighting when writing source. Therefore the Plaid projects provides support for following editors:

## Eclipse ##
URL: http://eclipse.org/ <br>
See <a href='GettingStarted#Plaid.md'>Syntax Highlighting in Eclipse</a>


<h2>Emacs</h2>
URL: <a href='http://www.gnu.org/software/emacs/'>http://www.gnu.org/software/emacs/</a>

To install the Plaid mode for Emacs download the <a href='http://plaid-lang.googlecode.com/hg/stdlib/misc/plaid-mode.el'>http://plaid-lang.googlecode.com/hg/stdlib/misc/plaid-mode.el</a> file and copy it into a place where emacs can find it. If you do not already have a user local place to install additional Emacs file you can create one as follows:<br>
<br>
<pre><code>mkdir -p $HOME/.emacs.d/lib/          # create local site for emacs extensions<br>
cp plaid-mode.el $HOME/.emacs.d/lib/  # copy plaid mode into local site <br>
</code></pre>

Additionally you have to make sure that Emacs will search in this new location by adding it to Emacs load path. Add the following code at the beginning of you '.emacs' file:<br>
<br>
<pre><code>(setq load-path (cons "~/.emacs.d/lib/" load-path))<br>
</code></pre>

To automatically load the Plaid mode when Emacs starts add the following line to your <code>.emacs</code> file:<br>
<br>
<pre><code>(require 'plaid-mode)<br>
</code></pre>

Once sucessfully installed the plaid mode should be automatically activated if you load Plaid source code file (i.e. <code>*.plaid</code>). The Plaid mode for Emacs only supports syntax highlighting and look (with the default colorscheme) like:<br>
<br>
<img src='http://plaid-lang.googlecode.com/hg/stdlib/misc/emacs_plaid_mode.png' />


<h2>Gedit</h2>
URL: <a href='http://projects.gnome.org/gedit/'>http://projects.gnome.org/gedit/</a>

Gedit is the default text editor of the Gnome deskop and should be installed on most Linux distributions. To install the Plaid mode for Gedit download <a href='http://plaid-lang.googlecode.com/hg/stdlib/misc/plaid.lang'>http://plaid-lang.googlecode.com/hg/stdlib/misc/plaid.lang</a> and copy it into a place where GtkSourceView can find it. To install it locally for a user just copy the file into <code>$HOME/.local/share/gtksourceview-2.0/language-specs</code> directory<br>
<br>
<pre><code>cp plaid.lang $HOME/.local/share/gtksourceview-2.0/language-specs<br>
</code></pre>

Once sucessfully installed the plaid mode should be automatically activated if you load Plaid source code file (i.e. <code>*.plaid</code>). The Plaid mode for Gedit look with the default colorscheme like:<br>
<br>
<img src='http://plaid-lang.googlecode.com/hg/stdlib/misc/gedit_plaid_mode.png' />