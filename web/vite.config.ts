import {defineConfig} from 'vite'
import react from '@vitejs/plugin-react'

export default defineConfig({
    esbuild: {
        tsconfigRaw: {
            compilerOptions: {
                experimentalDecorators: true,
            },
        },
    },
    plugins: [
        react()
    ],
})
