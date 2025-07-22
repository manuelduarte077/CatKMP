# Gitflow Workflow - CatKMP

## 📋 **Resumen del Flujo de Trabajo**

Este documento describe el flujo de trabajo Gitflow implementado en el proyecto CatKMP.

## 🌿 **Ramas Principales**

### `main` (Producción)
- **Propósito**: Contiene código de producción estable
- **Origen**: Solo acepta merges de `release/*` y `hotfix/*`
- **Protección**: No se puede hacer commit directo
- **Versión**: Siempre debe estar etiquetada con número de versión

### `develop` (Desarrollo)
- **Propósito**: Rama principal de desarrollo
- **Origen**: Contiene las últimas características entregadas
- **Fuente**: Para crear ramas `feature/*`
- **Destino**: Para merges de `feature/*`

## 🌱 **Ramas de Soporte**

### `feature/*` (Características)
- **Origen**: `develop`
- **Destino**: `develop`
- **Convención**: `feature/[issue-id]-descriptive-name`
- **Ejemplos**:
  - `feature/123-user-authentication`
  - `feature/456-cat-favorites`
  - `feature/789-ui-improvements`

### `release/*` (Releases)
- **Origen**: `develop`
- **Destino**: `main` y `develop`
- **Convención**: `release/vX.Y.Z`
- **Ejemplos**:
  - `release/v1.2.0`
  - `release/v2.0.0`
- **Actividades**: Solo correcciones de bugs, documentación y tareas de release

### `hotfix/*` (Correcciones Urgentes)
- **Origen**: `main`
- **Destino**: `main` y `develop`
- **Convención**: `hotfix/vX.Y.Z`
- **Ejemplos**:
  - `hotfix/v1.2.1`
  - `hotfix/v2.0.1`
- **Propósito**: Solo para correcciones urgentes de producción

## 📝 **Mensajes de Commit**

### Formato
```
type(scope): description

[optional body]

[optional footer(s)]
```

### Tipos
- `feat`: Nueva característica
- `fix`: Corrección de bug
- `docs`: Cambios en documentación
- `style`: Formato, punto y coma faltantes, etc.
- `refactor`: Refactorización de código
- `test`: Agregar tests
- `chore`: Tareas de mantenimiento

### Ejemplos
```bash
git commit -m "feat(auth): add OAuth2 integration"
git commit -m "fix(ui): resolve button alignment issue"
git commit -m "docs(api): update endpoint documentation"
git commit -m "refactor(core): optimize data processing"
git commit -m "test(auth): add login flow tests"
```

## 🔄 **Flujos de Trabajo**

### 1. **Desarrollo de Nueva Característica**

```bash
# 1. Asegurar que develop está actualizada
git checkout develop
git pull origin develop

# 2. Crear rama de feature
git checkout -b feature/123-new-feature

# 3. Desarrollar y hacer commits
git add .
git commit -m "feat(scope): implement new feature"

# 4. Push de la rama
git push -u origin feature/123-new-feature

# 5. Crear Pull Request a develop
# (Hacer en GitHub/GitLab)

# 6. Después del merge, eliminar rama local
git checkout develop
git pull origin develop
git branch -d feature/123-new-feature
```

### 2. **Proceso de Release**

```bash
# 1. Crear rama de release desde develop
git checkout develop
git pull origin develop
git checkout -b release/v1.2.0

# 2. Actualizar versiones y documentación
# - Actualizar build.gradle.kts
# - Actualizar CHANGELOG.md
# - Actualizar documentación

# 3. Commit de cambios de release
git add .
git commit -m "chore(release): prepare v1.2.0"

# 4. Push de la rama de release
git push -u origin release/v1.2.0

# 5. Crear Pull Request a main
# (Hacer en GitHub/GitLab)

# 6. Después del merge a main:
git checkout main
git pull origin main
git tag -a v1.2.0 -m "Release v1.2.0"
git push origin v1.2.0

# 7. Merge de release a develop
git checkout develop
git merge release/v1.2.0
git push origin develop

# 8. Eliminar rama de release
git branch -d release/v1.2.0
git push origin --delete release/v1.2.0
```

### 3. **Proceso de Hotfix**

```bash
# 1. Crear rama de hotfix desde main
git checkout main
git pull origin main
git checkout -b hotfix/v1.2.1

# 2. Corregir el bug
# - Hacer los cambios necesarios
# - Actualizar versión (patch)

# 3. Commit de la corrección
git add .
git commit -m "fix(scope): resolve critical bug"

# 4. Push de la rama de hotfix
git push -u origin hotfix/v1.2.1

# 5. Crear Pull Request a main
# (Hacer en GitHub/GitLab)

# 6. Después del merge a main:
git checkout main
git pull origin main
git tag -a v1.2.1 -m "Hotfix v1.2.1"
git push origin v1.2.1

# 7. Merge de hotfix a develop
git checkout develop
git merge hotfix/v1.2.1
git push origin develop

# 8. Eliminar rama de hotfix
git branch -d hotfix/v1.2.1
git push origin --delete hotfix/v1.2.1
```

## 🛡️ **Reglas de Protección de Ramas**

### `main` y `develop`
- ✅ Requerir Pull Request reviews
- ✅ Requerir que los checks de CI pasen
- ✅ Requerir que las ramas estén actualizadas
- ✅ Incluir administradores en las restricciones
- ❌ No permitir force pushes
- ❌ No permitir eliminaciones

### Configuración Recomendada
```yaml
# .github/branch-protection.yml
branches:
  - name: main
    protection:
      required_pull_request_reviews:
        required_approving_review_count: 1
      required_status_checks:
        strict: true
        contexts: []
      enforce_admins: true
      allow_force_pushes: false
      allow_deletions: false
      
  - name: develop
    protection:
      required_pull_request_reviews:
        required_approving_review_count: 1
      required_status_checks:
        strict: true
        contexts: []
      enforce_admins: true
      allow_force_pushes: false
      allow_deletions: false
```

## 📊 **Control de Versiones**

### Semantic Versioning (SemVer)
- **MAJOR**: Cambios incompatibles en la API
- **MINOR**: Funcionalidad nueva compatible
- **PATCH**: Correcciones de bugs compatibles

### Ejemplos de Versionado
- `1.0.0`: Primera versión estable
- `1.1.0`: Nueva funcionalidad (minor)
- `1.1.1`: Corrección de bug (patch)
- `2.0.0`: Cambio mayor incompatible

## 🧪 **Integración Continua**

### Workflows Recomendados
1. **Build y Test**: En cada PR
2. **Lint y Format**: En cada commit
3. **Security Scan**: En cada push a main
4. **Deploy Preview**: En cada PR
5. **Deploy Production**: En cada tag

## 📋 **Checklist de Release**

### Antes del Release
- [ ] Todas las features están en develop
- [ ] Tests pasan en develop
- [ ] Documentación actualizada
- [ ] CHANGELOG actualizado
- [ ] Versiones actualizadas

### Durante el Release
- [ ] Crear rama release/vX.Y.Z
- [ ] Actualizar versiones
- [ ] Crear PR a main
- [ ] Obtener aprobación
- [ ] Merge a main
- [ ] Crear tag
- [ ] Merge a develop
- [ ] Eliminar rama de release

### Después del Release
- [ ] Verificar deploy en producción
- [ ] Notificar al equipo
- [ ] Actualizar documentación de release
- [ ] Monitorear métricas

## 🚨 **Situaciones de Emergencia**

### Hotfix Urgente
1. Crear rama hotfix desde main
2. Corregir el problema
3. Testear exhaustivamente
4. Crear PR a main
5. Obtener aprobación urgente
6. Merge y deploy inmediato
7. Merge a develop después

### Rollback
1. Identificar el commit problemático
2. Crear hotfix con la versión anterior
3. Deploy inmediato
4. Investigar el problema
5. Corregir en develop

## 📚 **Recursos Adicionales**

- [Gitflow Workflow](https://nvie.com/posts/a-successful-git-branching-model/)
- [Conventional Commits](https://www.conventionalcommits.org/)
- [Semantic Versioning](https://semver.org/)
- [GitHub Flow](https://guides.github.com/introduction/flow/)

## 🤝 **Colaboración**

### Code Review
- Mínimo 1 aprobación requerida
- Todos los checks deben pasar
- Resolver comentarios antes del merge
- Usar templates de PR

### Comunicación
- Usar issues para tracking
- Documentar decisiones importantes
- Mantener actualizado el CHANGELOG
- Comunicar releases al equipo 