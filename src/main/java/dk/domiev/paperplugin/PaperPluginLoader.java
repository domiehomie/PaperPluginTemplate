package dk.domiev.paperplugin;

import io.papermc.paper.plugin.loader.PluginClasspathBuilder;
import io.papermc.paper.plugin.loader.PluginLoader;
import io.papermc.paper.plugin.loader.library.impl.MavenLibraryResolver;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.graph.Dependency;

public class PaperPluginLoader implements PluginLoader {
    @Override
    public void classloader(PluginClasspathBuilder pluginClasspathBuilder) {
        MavenLibraryResolver resolver = new MavenLibraryResolver();
//        resolver.addDependency(new Dependency(new DefaultArtifact("org.spongepowered:configurate-core:4.1.2"), null));
//        resolver.addDependency(new Dependency(new DefaultArtifact("org.spongepowered:configurate-hocon:4.1.2"), null));
        pluginClasspathBuilder.addLibrary(resolver);
    }
}
