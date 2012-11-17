package org.lightadmin.core.config.domain.scope;

import com.google.common.base.Predicates;
import org.junit.Test;
import org.lightadmin.core.config.bootstrap.parsing.configuration.DomainConfigurationUnitType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.lightadmin.core.config.domain.scope.ScopeMetadataUtils.all;
import static org.lightadmin.core.config.domain.scope.ScopeMetadataUtils.filter;

public class DefaultScopesConfigurationUnitBuilderTest {

	@Test
	public void defaultConfigurationUnitCreatedForDomainType() {
		ScopesConfigurationUnit configurationUnit = defaultScopesBuilder().build();

		assertEquals( DomainConfigurationUnitType.SCOPES, configurationUnit.getDomainConfigurationUnitType() );
		assertEquals( DomainType.class, configurationUnit.getDomainType() );

		assertScopesDefined( configurationUnit, "All" );
	}

	@Test
	public void allScopesDefined() throws Exception {
		ScopesConfigurationUnit configurationUnit = defaultScopesBuilder()
			.scope( "Scope #1", all() )
			.scope( "Scope #2", filter( Predicates.alwaysTrue() ) )
			.build();

		assertScopesDefined( configurationUnit, "Scope #1", "Scope #2" );
	}

	private void assertScopesDefined( ScopesConfigurationUnit configurationUnit, String... scopeNames ) {
		for ( String scopeName : scopeNames ) {
			assertNotNull( configurationUnit.getScope( scopeName ) );
		}
	}

	private ScopesConfigurationUnitBuilder defaultScopesBuilder() {
		return new DefaultScopesConfigurationUnitBuilder( DomainType.class );
	}

	private static class DomainType {

	}
}