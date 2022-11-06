import React, { useCallback, useState } from 'react';
import { Button } from '@mui/material';
import { create } from '../../../api/posts/postsApi';

export function PostPageCreate(): React.ReactElement {
  const [text, setText] = useState<string>();
  const [photos, setPhotos] = useState<string[]>();

  const onCreate = useCallback(() => {
    create({ userId: '1', photos, description: text }).then((x) => console.log(x));
  }, []);
  return (
    <div>
      <input type="text" value={text} onChange={(x) => setText(x.target.value)} />
      <Button onClick={onCreate}>Создать</Button>
    </div>
  );
}
